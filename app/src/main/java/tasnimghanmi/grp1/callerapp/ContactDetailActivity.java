package tasnimghanmi.grp1.callerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView tvName, tvPseudo, tvPhone;
    private Button btnCall, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        // Bind views
        tvName = findViewById(R.id.tv_name);
        tvPseudo = findViewById(R.id.tv_pseudo);
        tvPhone = findViewById(R.id.tv_phone);
        btnCall = findViewById(R.id.btn_call);
        btnExit = findViewById(R.id.btn_exit);

        // Retrieve data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String pseudo = intent.getStringExtra("PSEUDO");
        String phone = intent.getStringExtra("PHONE");

        // handle missing phone number
        if (phone == null || phone.trim().isEmpty()) {
            Toast.makeText(this, "Phone number not available", Toast.LENGTH_SHORT).show();
            phone = "";
        }

        // fill UI fields
        tvName.setText("Name: " + (name != null ? name : "Unknown"));
        tvPseudo.setText("Pseudo: " + (pseudo != null ? pseudo : "N/A"));
        tvPhone.setText("Phone: " + phone);

        // 👇 final FINAL copy of phone for use in lambda
        final String finalPhone = phone.trim(); // Now it's safe to use

        // call btn: Open dialer
        btnCall.setOnClickListener(v -> {
            if (!finalPhone.isEmpty()) {
                //creates an Intent that tells Android I want to open the dialer app.
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                //attaches the phone to the Intent (URI="tel:1238777")
                callIntent.setData(Uri.parse("tel:" + finalPhone));
                //chekcs of an app installed that can handle this Intent
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(callIntent);
                } else {
                    Toast.makeText(this, "No phone app found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Phone number is missing", Toast.LENGTH_SHORT).show();
            }
        });

        // EXIT BUTTON
        btnExit.setOnClickListener(v -> finish());
    }
}