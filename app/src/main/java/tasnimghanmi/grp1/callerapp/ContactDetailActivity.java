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

        // Handle missing phone number
        if (phone == null || phone.trim().isEmpty()) {
            Toast.makeText(this, "Phone number not available", Toast.LENGTH_SHORT).show();
            phone = "";
        }

        // Fill UI fields
        tvName.setText("Name: " + (name != null ? name : "Unknown"));
        tvPseudo.setText("Pseudo: " + (pseudo != null ? pseudo : "N/A"));
        tvPhone.setText("Phone: " + phone);

        // 👇 Create a FINAL copy of phone for use in lambda
        final String finalPhone = phone.trim(); // Now it's safe to use

        // CALL BUTTON: Open dialer
        btnCall.setOnClickListener(v -> {
            if (!finalPhone.isEmpty()) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + finalPhone));
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