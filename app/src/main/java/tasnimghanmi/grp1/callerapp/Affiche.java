// File: Affiche.java
package tasnimghanmi.grp1.callerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Affiche extends AppCompatActivity {

    private SearchView search;
    private ListView list;
    private Button btnBack;
    private CustomContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

        // Bind UI elements
        search = findViewById(R.id.search_ajout);
        list = findViewById(R.id.list_affiche);
        btnBack = findViewById(R.id.btn_back);

        // Set up adapter
        adapter = new CustomContactAdapter(this, Acceuil.liste_contact);
        //attaches the adapter to the ListView so it can display contacts.
        list.setAdapter(adapter);

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // On contact click → open detail activity
        //adds listner for clicks on the elements
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = Acceuil.liste_contact.get(position);

                // Launch ContactDetailActivity
                Intent intent = new Intent(Affiche.this, ContactDetailActivity.class);
                intent.putExtra("NAME", selectedContact.getNom());
                intent.putExtra("PSEUDO", selectedContact.getPseudo());
                intent.putExtra("PHONE", selectedContact.getNum());
                startActivity(intent);
            }
        });

        //recherche


    }
}