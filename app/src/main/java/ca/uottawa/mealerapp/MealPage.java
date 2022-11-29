package ca.uottawa.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.uottawa.mealerapp.userclasses.UsernameConversion;

public class MealPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_page);

        TextView title = findViewById(R.id.meal_title);
        Switch isOfferedSwitch = findViewById(R.id.is_offered_switch);
        Button deleteMealButton = findViewById(R.id.delete_meal_button);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        String username = getIntent().getStringExtra("username");
        String mealName = getIntent().getStringExtra("meal");

        title.setText(mealName);

        DatabaseReference mealRef = ref.child("cooks").child(UsernameConversion
                .encode(username)).child("menu").child(mealName);

        isOfferedSwitch.setOnClickListener(view ->
                mealRef.child("isOffered").setValue(isOfferedSwitch.isChecked()));

        deleteMealButton.setOnClickListener(view -> {
            if (!isOfferedSwitch.isChecked()) {
                mealRef.removeValue();
                Intent intent = new Intent(this, MealDisplay.class);
                intent.putExtra("username", username);
                startActivity(intent);
            } else
                Toast.makeText(this, "Cannot delete a meal that is currently offered.", Toast.LENGTH_SHORT).show();
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean isOffered = snapshot.child("cooks").child(UsernameConversion
                        .encode(username)).child("menu").child(mealName).child("isOffered").getValue(Boolean.class);
                if (isOffered != null)
                    isOfferedSwitch.setChecked(isOffered);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}