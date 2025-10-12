package tasnimghanmi.grp1.callerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ajout extends AppCompatActivity {

    private Button btnajout, btnannuler;
    private EditText nom, pseudo, tel;
    private ContactManager contactManager; // Database manager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajout);

        // Initialize buttons and edit texts
        btnajout = findViewById(R.id.ajout_ajout);
        btnannuler = findViewById(R.id.annuler_ajout);
        nom = findViewById(R.id.nom_ajout);
        pseudo = findViewById(R.id.pseudo_ajout);
        tel = findViewById(R.id.tel_ajout);

        // Open the database
        contactManager = new ContactManager(this);
        contactManager.ouvrir();

        // Cancel button: close the activity
        btnannuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current screen
            }
        });

        // Add button: save contact and clear fields
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from input fields
                String name = nom.getText().toString().trim();
                String psd = pseudo.getText().toString().trim();
                String phone = tel.getText().toString().trim();

                // Validate if any field is empty
                if (name.isEmpty() || psd.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(Ajout.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add contact to the database
                long res = contactManager.ajout(name, psd, phone);

                if (res != -1) {
                    Toast.makeText(Ajout.this, "Contact added successfully!", Toast.LENGTH_SHORT).show();

                    // Clear the input fields
                    nom.setText("");
                    pseudo.setText("");
                    tel.setText("");

                    // Optional: Reset focus to the first field
                    nom.requestFocus();
                } else {
                    Toast.makeText(Ajout.this, "Error adding contact!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle window insets (for edge-to-edge UI)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactManager.fermer(); // Close DB connection properly
    }
}
