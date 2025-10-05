package tasnimghanmi.grp1.callerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Affiche extends AppCompatActivity {

    private SearchView search;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_affiche);
        
        search=findViewById(R.id.search_ajout);
        list=findViewById(R.id.list_affiche);

        ArrayAdapter ad=new ArrayAdapter<>(Affiche.this, android.R.layout.simple_list_item_1,Acceuil.liste_contact);

        list.setAdapter(ad);




    }
}