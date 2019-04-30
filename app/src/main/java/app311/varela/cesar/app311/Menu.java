package app311.varela.cesar.app311;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //ImageButton para Registrar Incidencia
        ImageButton btnIncidencia = (ImageButton) findViewById(R.id.btnIncidencia);
        btnIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RegistrarIncidencia.class);
                startActivityForResult(intent,0);
            }
        });

        //ImageButton para Editar perfil
        ImageButton btnEditar = (ImageButton) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Registro.class);
                intent.putExtra("MODE",1);
                startActivityForResult(intent,0);

            }
        });

        //ImageButton para Editar perfil
        ImageButton btnHistorial = (ImageButton) findViewById(R.id.btnHistorico);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Historial.class);
                intent.putExtra("MODE",1);
                startActivityForResult(intent,0);

            }
        });


    }
}
