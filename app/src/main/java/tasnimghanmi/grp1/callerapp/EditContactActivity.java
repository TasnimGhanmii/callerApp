package tasnimghanmi.grp1.callerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity {

    private EditText etName, etPseudo, etPhone;
    private Button btnSave, btnCancel;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        etName = findViewById(R.id.et_name);
        etPseudo = findViewById(R.id.et_pseudo);
        etPhone = findViewById(R.id.et_phone);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        // get data
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String pseudo = intent.getStringExtra("PSEUDO");
        String phone = intent.getStringExtra("PHONE");
        position = intent.getIntExtra("POSITION", -1);

        // fill fields
        etName.setText(name);
        etPseudo.setText(pseudo);
        etPhone.setText(phone);

        // save
        btnSave.setOnClickListener(v -> {
            String newName = etName.getText().toString().trim();
            String newPseudo = etPseudo.getText().toString().trim();
            String newPhone = etPhone.getText().toString().trim();

            if (newName.isEmpty() || newPhone.isEmpty()) {
                Toast.makeText(this, "Name and phone cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // update contact in global list
            if (position != -1) {
                Contact contact = Acceuil.liste_contact.get(position);
                contact.setNom(newName);
                contact.setPseudo(newPseudo);
                contact.setNum(newPhone);
            }

            // return updated values
            Intent resultIntent = new Intent();
            resultIntent.putExtra("NAME", newName);
            resultIntent.putExtra("PSEUDO", newPseudo);
            resultIntent.putExtra("PHONE", newPhone);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
