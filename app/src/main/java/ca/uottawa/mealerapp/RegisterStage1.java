package ca.uottawa.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterStage1 extends AppCompatActivity {
    // Name of the key used to store the content of the inputs in the activity's intent
    private static final String firstNameKey = "first name";
    private static final String lastNameKey = "last name";
    private static final String emailKey = "email";
    private static final String passwordKey = "password";

    private static final String baseMsg = "Please enter your ";
    private static final String invalidEmailMsg = "Invalid email";

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_stage1);

        firstNameInput = findViewById(R.id.register_first_name);
        lastNameInput = findViewById(R.id.register_last_name);
        emailInput = findViewById(R.id.register_email);
        passwordInput = findViewById(R.id.register_password);

        TextView login_return = findViewById(R.id.return_login_text);
        login_return.setOnClickListener(view ->
                startActivity(new Intent(RegisterStage1.this, LoginPage.class)));

        Button clientRegister = findViewById(R.id.client_register_button);
        clientRegister.setOnClickListener(view -> {
            String validationStatement = validateInputs();
            if (validationStatement.equals("valid")) {
                Intent intent = new Intent(RegisterStage1.this, ClientRegister.class);

                intent.putExtra(firstNameKey, firstNameInput.getText().toString());
                intent.putExtra(lastNameKey, lastNameInput.getText().toString());
                intent.putExtra(emailKey, emailInput.getText().toString());
                intent.putExtra(passwordKey, passwordInput.getText().toString());

                startActivity(intent);
            } else {
                Toast.makeText(RegisterStage1.this, validationStatement, Toast.LENGTH_LONG).show();
            }
        });
    }

    private String validateInputs() {
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (firstName.equals(""))
            return baseMsg + firstNameKey;

        if (lastName.equals(""))
            return baseMsg + lastNameKey;

        if (email.equals(""))
            return baseMsg + emailKey;

        if (password.equals(""))
            return baseMsg + passwordKey;

        if (!email.contains("@"))
            return invalidEmailMsg;

        String[] splitEmail = email.split("@");
        if (splitEmail.length <= 1)
            return invalidEmailMsg;

        if (splitEmail[1].contains(".")) {
            String[] domainSplit = splitEmail[1].split("\\.");
            if (domainSplit.length <= 1)
                return invalidEmailMsg;
        }

        return "valid";
    }
}