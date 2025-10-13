package tasnimghanmi.grp1.callerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity {

    private EditText et_name, et_pseudo, et_phone;
    private Button btnSave, btnCancel;
    private int id;
    private ContactManager contactManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        et_name = findViewById(R.id.et_name);
        et_pseudo = findViewById(R.id.et_pseudo);
        et_phone = findViewById(R.id.et_phone);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        contactManager = new ContactManager(this);
        contactManager.ouvrir();

        Intent intent = getIntent();
        id = intent.getIntExtra("ID", -1);
        et_name.setText(intent.getStringExtra("NAME"));
        et_pseudo.setText(intent.getStringExtra("PSEUDO"));
        et_phone.setText(intent.getStringExtra("PHONE"));

        btnSave.setOnClickListener(v -> {
            String newName = et_name.getText().toString().trim();
            String newPseudo = et_pseudo.getText().toString().trim();
            String newPhone = et_phone.getText().toString().trim();

            if (newName.isEmpty() || newPseudo.isEmpty() || newPhone.isEmpty()) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            contactManager.updateContact(id, newName, newPseudo, newPhone);
            Toast.makeText(this, "Contact updated", Toast.LENGTH_SHORT).show();

            Intent result = new Intent();
            result.putExtra("NAME", newName);
            result.putExtra("PSEUDO", newPseudo);
            result.putExtra("PHONE", newPhone);
            //sends the data back to the activity that launched it via a launcher (contactdetail)
            setResult(RESULT_OK, result);
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactManager.fermer();
    }
}
