package ca.uottawa.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RegisterStage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_stage1);

        TextView login_return = findViewById(R.id.return_login_text);
        login_return.setOnClickListener(view ->
                startActivity(new Intent(RegisterStage1.this, LoginPage.class)));

        Button clientRegister = findViewById(R.id.client_register_button);
        clientRegister.setOnClickListener(view ->
                startActivity(new Intent(RegisterStage1.this, ClientRegister.class)));
    }
}