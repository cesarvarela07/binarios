package app311.varela.cesar.app311;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    ImageButton pots, hist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        pots = (ImageButton)findViewById(R.id.btnIncidencia);
        pots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAcvtividadPost();
            }
        });


        hist = (ImageButton)findViewById(R.id.btnHistorico);
        hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAcvtividadHistorico();
            }
        });

    }


    public void openAcvtividadPost(){
        Intent intent = new Intent(this, RegistrarIncidencia.class);
        startActivity(intent);

    }

    public void openAcvtividadHistorico(){
        Intent intent = new Intent(this, Historial.class);
        startActivity(intent);

    }

}
