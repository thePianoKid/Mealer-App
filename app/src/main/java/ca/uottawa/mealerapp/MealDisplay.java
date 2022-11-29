package ca.uottawa.mealerapp;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import ca.uottawa.mealerapp.mealclasses.Meal;
import ca.uottawa.mealerapp.userclasses.UsernameConversion;

public class MealDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_display);

        String encodedUsername = UsernameConversion.encode(getIntent().getStringExtra("username"));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("cooks")
                .child(encodedUsername).child("menu");

        ListView listView = findViewById(R.id.list_view);
        FloatingActionButton addMealButton = findViewById(R.id.add_meal_button);

        addMealButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddMeal.class);
            intent.putExtra("username", encodedUsername);
            startActivity(intent);
        });

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String mealName;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mealName = list.get(i);
                Intent intent = new Intent(MealDisplay.this, MealPage.class);
                intent.putExtra("username", encodedUsername);
                intent.putExtra("meal", mealName);
                startActivity(intent);
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Boolean isOffered = dataSnapshot.child("offered").getValue(Boolean.class);
                    if (isOffered != null) {
                        if (isOffered)
                            list.add(dataSnapshot.getKey() + " (Offered)");
                        else
                            list.add(dataSnapshot.getKey());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}