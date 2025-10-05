package tasnimghanmi.grp1.callerapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Affiche extends AppCompatActivity {

    private SearchView search;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche); // No EdgeToEdge needed unless required

        search = findViewById(R.id.search_ajout);
        list = findViewById(R.id.list_affiche);

        // Use custom adapter
        CustomContactAdapter adapter = new CustomContactAdapter(this, Acceuil.liste_contact);
        list.setAdapter(adapter);

        // Optional: Add search functionality later
    }
}