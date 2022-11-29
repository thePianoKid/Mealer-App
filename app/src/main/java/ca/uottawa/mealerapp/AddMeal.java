package ca.uottawa.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ca.uottawa.mealerapp.mealclasses.Meal;
import ca.uottawa.mealerapp.userclasses.UsernameConversion;

public class AddMeal extends AppCompatActivity {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String encodedUsername = UsernameConversion.encode(getIntent().getStringExtra("username"));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("cooks")
                .child(encodedUsername).child("menu");

        setContentView(R.layout.activity_add_meal);
        Button addMeal = findViewById(R.id.confirm_meal_button);
        EditText name = findViewById(R.id.meal_name_input);
        EditText type = findViewById(R.id.meal_type_input);
        EditText cuisine = findViewById(R.id.meal_cuisine_input);
        EditText ingredients = findViewById(R.id.meal_ingredients_input);
        EditText allergens = findViewById(R.id.meal_allergens_input);
        EditText price = findViewById(R.id.meal_price_input);
        EditText description = findViewById(R.id.meal_description_input);
        Switch isOfferedSwitch = findViewById(R.id.is_offered_toggle);

        addMeal.setOnClickListener(view -> {
            String nameStr = name.getText().toString();
            Meal meal = new Meal(
                    nameStr,
                    type.getText().toString(),
                    cuisine.getText().toString(),
                    ingredients.getText().toString(),
                    allergens.getText().toString(),
                    price.getText().toString(),
                    description.getText().toString(),
                    isOfferedSwitch.isChecked()
            );
            ref.child(nameStr).setValue(meal);
            Intent intent = new Intent(this, MealDisplay.class);
            intent.putExtra("username", encodedUsername);
            startActivity(intent);
        });
    }
}