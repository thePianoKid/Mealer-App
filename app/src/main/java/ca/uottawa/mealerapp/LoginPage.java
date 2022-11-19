package ca.uottawa.mealerapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.uottawa.mealerapp.userclasses.UsernameConversion;

public class LoginPage extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerText = findViewById(R.id.register_text);
        Button signInButton = findViewById(R.id.signin_button);
        EditText usernameInput = findViewById(R.id.login_username);
        EditText passwordInput = findViewById(R.id.login_password);

        registerText.setOnClickListener(view ->
                startActivity(new Intent(LoginPage.this, RegisterStage1.class)));

        signInButton.setOnClickListener(view -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        Log.i(TAG, UsernameConversion.encode(username));
                        String correctPassword = snapshot.child("cooks").child(UsernameConversion
                                .encode(username)).child("password").getValue().toString();

                        if (correctPassword.equals(password)) {
                            Intent intent = new Intent(LoginPage.this, MealDisplay.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(LoginPage.this, "Incorrect password",
                                    Toast.LENGTH_LONG).show();

                    } catch (NullPointerException e) {
                        Toast.makeText(LoginPage.this, "User not found.",
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        });
    }
}