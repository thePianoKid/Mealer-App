package ca.uottawa.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ClientRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_register);

        TextView return_stage1_text = findViewById(R.id.return_stage1_text);
        return_stage1_text.setOnClickListener(view ->
                startActivity(new Intent(ClientRegister.this, RegisterStage1.class)));

    }
}