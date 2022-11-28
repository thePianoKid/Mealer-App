package ca.uottawa.mealerapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.ArrayList;
import java.util.Locale;

import ca.uottawa.mealerapp.mealclasses.Meal;
import ca.uottawa.mealerapp.userclasses.UsernameConversion;

public class MealDisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_display);

        String username = UsernameConversion.encode(getIntent().getStringExtra("username"));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("cooks")
                .child(username).child("menu");

        ListView listView = findViewById(R.id.list_view);
        EditText mealName = findViewById(R.id.meal_name);
        Button mealSearchButton = findViewById(R.id.meal_search_button);
        FloatingActionButton addMealButton = findViewById(R.id.add_meal_button);

        addMealButton.setOnClickListener(view -> startActivity(new Intent(this, AddMeal.class)));

        // TODO: Make the list items clickable & make the input for filtering the meals
        mealSearchButton.setOnClickListener(view -> ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String trimmedMealName = mealName.getText().toString().trim();
                ref.orderByKey().equalTo(trimmedMealName).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Intent intent = new Intent(MealDisplay.this, MealPage.class);
                            intent.putExtra("username", username);
                            intent.putExtra("meal", trimmedMealName);
                            startActivity(intent);
                        } else
                            Toast.makeText(MealDisplay.this,
                                    "Invalid meal name. Search is case sensitive.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item,
                list);

        listView.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Boolean isOffered = dataSnapshot.child("isOffered").getValue(Boolean.class);
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

    private void addMeal(DatabaseReference databaseReference, Meal meal) {
        databaseReference.setValue(meal);
    }
}