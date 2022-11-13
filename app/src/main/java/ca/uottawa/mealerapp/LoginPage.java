package ca.uottawa.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView register_text = findViewById(R.id.register_text);
        register_text.setOnClickListener(view ->
                startActivity(new Intent(LoginPage.this, RegisterStage1.class)));
    }
}