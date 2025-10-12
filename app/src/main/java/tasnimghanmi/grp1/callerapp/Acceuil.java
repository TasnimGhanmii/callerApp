package tasnimghanmi.grp1.callerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Acceuil extends AppCompatActivity {
    private Button btnaff, btnajout, btnqte;
    private TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_acceuil);

        btnaff = findViewById(R.id.btnaff_acc);
        btnajout = findViewById(R.id.btnajout_acc);
        tvUser = findViewById(R.id.tvuser_acc);
        btnqte = findViewById(R.id.qte_acc);

        Intent x = this.getIntent();
        Bundle b = x.getExtras();
        String u = b.getString("user");
        tvUser.setText("Acceuil de " + u);

        btnajout.setOnClickListener(v -> {
            Intent i = new Intent(Acceuil.this, Ajout.class);
            startActivity(i);
        });

        btnaff.setOnClickListener(v -> {
            Intent i = new Intent(Acceuil.this, Affiche.class);
            startActivity(i);
        });

        btnqte.setOnClickListener(v -> Acceuil.this.finish());
    }
}
