package tasnimghanmi.grp1.callerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity{
    EditText ednom,edmp;
    private Button btnval;
    private Button btnqte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //lanced auto first thing
        super.onCreate(savedInstanceState);
        //for edged screen phones
        EdgeToEdge.enable(this);
        //set xml file and show it on the screen
        //R: ressources
        setContentView(R.layout.activity_main);

        ednom=findViewById(R.id.ednom_auth);
        edmp=findViewById(R.id.edmp_auth);
        btnval=findViewById(R.id.btnval_auth);
        btnqte=findViewById(R.id.btnqte_auth);

        /*btnqte.setOnClickListener(this);
        btnval.setOnClickListener(this);*/

        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                //this.(..): refers to the anonymous class
                MainActivity.this.finish();
            }
        });

        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom=ednom.getText().toString();
                String mp=edmp.getText().toString();

                if(nom.equalsIgnoreCase(("tasnim"))&&mp.equalsIgnoreCase("000"))
                {
                    Intent i=new Intent(MainActivity.this,Acceuil.class);
                    i.putExtra("user",nom);
                    startActivity(i);
                    MainActivity.this.finish();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        //actif
        //twariha fil ecran
        super.onStart();
    }

    @Override
    protected void onStop() {
        //minim in backround
        //t7otha en arrier plan
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*@Override
    public void onClick(View v) {
        if(v==btnqte)
        {

        }
        else
        {

        }
    }*/
}