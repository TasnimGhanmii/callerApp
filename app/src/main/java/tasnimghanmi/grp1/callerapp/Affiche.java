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
    private ArrayList<Contact> filteredContacts;

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

        // List item click → open detail activity
        list.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Contact selectedContact = filteredContacts.get(position); // use filtered list
            Intent intent = new Intent(Affiche.this, ContactDetailActivity.class);
            intent.putExtra("ID", selectedContact.getId());
            intent.putExtra("NAME", selectedContact.getNom());
            intent.putExtra("PSEUDO", selectedContact.getPseudo());
            intent.putExtra("PHONE", selectedContact.getNum());
            contactDetailLauncher.launch(intent);
        });

        // SEARCH VIEW
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterContacts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterContacts(newText);
                return true;
            }
        });
    }

    private void loadContacts() {
        contacts = contactManager.getAllContacts();
        filteredContacts = new ArrayList<>(contacts);

        if (contacts.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            adapter = new CustomContactAdapter(this, filteredContacts);
            list.setAdapter(adapter);
        }
    }

    private void filterContacts(String query) {
        filteredContacts.clear();
        if (query == null || query.trim().isEmpty()) {
            filteredContacts.addAll(contacts);
        } else {
            String lowerQuery = query.toLowerCase();
            for (Contact c : contacts) {
                if (c.getNom().toLowerCase().contains(lowerQuery)
                        || c.getPseudo().toLowerCase().contains(lowerQuery)
                        || c.getNum().toLowerCase().contains(lowerQuery)) {
                    filteredContacts.add(c);
                }
            }
        }

        // Show empty text if no results
        if (filteredContacts.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
        }

        adapter.notifyDataSetChanged();
    }

    private final ActivityResultLauncher<Intent> contactDetailLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    loadContacts(); // refresh after editing or adding
                }
            });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactManager.fermer();
    }
}
