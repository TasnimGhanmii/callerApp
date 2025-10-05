package tasnimghanmi.grp1.callerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ajout extends AppCompatActivity {


    private Button btnajout,btnannuler;
    private EditText nom,pseudo,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajout);


        btnajout=findViewById(R.id.ajout_ajout);
        btnannuler=findViewById(R.id.annuler_ajout);

        nom=findViewById(R.id.nom_ajout);
        pseudo=findViewById(R.id.pseudo_ajout);
        tel=findViewById(R.id.tel_ajout);

        btnannuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nom.getText().toString();
                String psd=pseudo.getText().toString();
                String phone=tel.getText().toString();

                Contact c=new Contact(name,psd,phone);
                Acceuil.liste_contact.add(c);

                Toast.makeText(Ajout.this, "Contact added successefully!", Toast.LENGTH_SHORT).show();

            }
        });










    }
}