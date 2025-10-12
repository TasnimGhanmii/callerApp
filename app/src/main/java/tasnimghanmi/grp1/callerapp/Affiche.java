package tasnimghanmi.grp1.callerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Affiche extends AppCompatActivity {

    private SearchView search;
    private ListView list;
    private Button btnBack;
    private TextView tvEmpty;
    private CustomContactAdapter adapter;
    private ContactManager contactManager;
    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

        search = findViewById(R.id.search_ajout);
        list = findViewById(R.id.list_affiche);
        btnBack = findViewById(R.id.btn_back);
        tvEmpty = findViewById(R.id.tv_empty);

        contactManager = new ContactManager(this);
        contactManager.ouvrir();

        loadContacts();

        btnBack.setOnClickListener(v -> finish());

        list.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Contact selectedContact = contacts.get(position);
            Intent intent = new Intent(Affiche.this, ContactDetailActivity.class);
            intent.putExtra("ID", selectedContact.getId());
            intent.putExtra("NAME", selectedContact.getNom());
            intent.putExtra("PSEUDO", selectedContact.getPseudo());
            intent.putExtra("PHONE", selectedContact.getNum());
            contactDetailLauncher.launch(intent);
        });
    }

    private void loadContacts() {
        contacts = contactManager.getAllContacts();
        if (contacts.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            adapter = new CustomContactAdapter(this, contacts);
            list.setAdapter(adapter);
        }
    }

    private final ActivityResultLauncher<Intent> contactDetailLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    loadContacts(); // refresh after editing
                }
            });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactManager.fermer();
    }
}
