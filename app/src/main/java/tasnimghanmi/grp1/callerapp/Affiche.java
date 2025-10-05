package tasnimghanmi.grp1.callerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class Affiche extends AppCompatActivity {

    private SearchView search;
    private ListView list;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

        // Bind UI elements
        search = findViewById(R.id.search_ajout);
        list = findViewById(R.id.list_affiche);
        btnBack = findViewById(R.id.btn_back);

        // Set up custom adapter for ListView using the contact list from Acceuil
        CustomContactAdapter adapter = new CustomContactAdapter(this, Acceuil.liste_contact);
        list.setAdapter(adapter);

        // Handle back button click
        btnBack.setOnClickListener(v -> {
            finish(); // Close this activity and go back to Acceuil
        });

        // Optional: Add search functionality later
        // Example:
        /*
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        */
    }
}