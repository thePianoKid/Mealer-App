package ca.uottawa.mealerapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ComplaintPage extends AppCompatActivity {
    // Number of milliseconds in one week
    static final Long millsInWeek = (long) 604800016.56;
    static final Long millsInMonth = (long) 2629800000.0;
    static final double millsInYear = (long) 31557600000.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        String[] banOptions = {"1 Week", "2 Weeks", "1 Month", "2 Months", "6 Months", "1 Year", "Permanently"};
        String cookUsername;

        String complaintKey = getIntent().getStringExtra("complaintKey");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        TextView complaintTitle = findViewById(R.id.complaint_title);
        TextView cookText = findViewById(R.id.cook_text);
        TextView clientText = findViewById(R.id.client_text);
        TextView descriptionText = findViewById(R.id.description_text);
        Spinner banningDropdown = findViewById(R.id.banning_dropdown);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, banOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        banningDropdown.setAdapter(adapter);

        complaintTitle.setText(complaintKey);

        banningDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    startActivity(new Intent(ComplaintPage.this, AdminPage.class));
                    Toast.makeText(ComplaintPage.this, "Cook banned for " + banOptions[i], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cookText.setText(snapshot.child("complaints").child(complaintKey).child("cook").getValue(String.class));
                clientText.setText(snapshot.child("complaints").child(complaintKey).child("client").getValue(String.class));
                descriptionText.setText(snapshot.child("complaints").child(complaintKey).child("description").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void test() {
        long milliSec = System.currentTimeMillis();

        // Creating date format
        DateFormat simple = new SimpleDateFormat(
                "dd MMM yyyy", Locale.CANADA);

        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(milliSec);

        // Formatting Date according to the
        // given format
        System.out.println(simple.format(result));
    }
}