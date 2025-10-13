package tasnimghanmi.grp1.callerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView tvName, tvPseudo, tvPhone;
    private Button btnCall, btnExit, btnEdit;

    private String name, pseudo, phone;
    private int id;
    private ContactManager contactManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        tvName = findViewById(R.id.tv_name);
        tvPseudo = findViewById(R.id.tv_pseudo);
        tvPhone = findViewById(R.id.tv_phone);
        btnCall = findViewById(R.id.btn_call);
        btnExit = findViewById(R.id.btn_exit);
        btnEdit = findViewById(R.id.btn_edit);

        contactManager = new ContactManager(this);
        contactManager.ouvrir();

        Intent intent = getIntent();
        id = intent.getIntExtra("ID", -1);
        name = intent.getStringExtra("NAME");
        pseudo = intent.getStringExtra("PSEUDO");
        phone = intent.getStringExtra("PHONE");

        updateUI();

        btnCall.setOnClickListener(v -> {
            if (!phone.trim().isEmpty()) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phone));
                if (callIntent.resolveActivity(getPackageManager()) != null) startActivity(callIntent);
                else Toast.makeText(this, "No phone app found", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(this, "Phone number is missing", Toast.LENGTH_SHORT).show();
        });

        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(ContactDetailActivity.this, EditContactActivity.class);
            editIntent.putExtra("ID", id);
            editIntent.putExtra("NAME", name);
            editIntent.putExtra("PSEUDO", pseudo);
            editIntent.putExtra("PHONE", phone);
            editContactLauncher.launch(editIntent);
        });

        btnExit.setOnClickListener(v -> finish());
    }

    private void updateUI() {
        tvName.setText("Name: " + name);
        tvPseudo.setText("Pseudo: " + pseudo);
        tvPhone.setText("Phone: " + phone);
    }

    //a modern Android API that launches another activity and receives a result back.
    private final ActivityResultLauncher<Intent> editContactLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                //When the user finishes editing and the EditContactActivity calls setResult(RESULT_OK, intent) before closing
                //result fct successful & there is an intent carrying the data
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    name = result.getData().getStringExtra("NAME");
                    pseudo = result.getData().getStringExtra("PSEUDO");
                    phone = result.getData().getStringExtra("PHONE");
                    updateUI();
                    setResult(RESULT_OK); // tells Affiche to refresh list
                }
            });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactManager.fermer();
    }
}
