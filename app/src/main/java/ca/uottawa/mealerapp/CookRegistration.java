package ca.uottawa.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import ca.uottawa.mealerapp.userclasses.Cook;

public class CookRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_registration);

        Intent intent = getIntent();

        Cook cook = new Cook(intent.getExtras().getString("email"),
                intent.getExtras().getString("password"));
    }
}