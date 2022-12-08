package ca.uottawa.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RateCook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String[] starChoices = {"1", "2", "3", "4", "5"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_cook);

        Spinner starSelect = findViewById(R.id.star_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, starChoices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        starSelect.setAdapter(adapter);
    }
}