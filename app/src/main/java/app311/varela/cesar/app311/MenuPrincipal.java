package app311.varela.cesar.app311;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        //ImageButton para Registrar Incidencia
        ImageButton ibRegIncidenia = (ImageButton) findViewById(R.id.ibRegistrarIncidencia);
        ibRegIncidenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RegistroIncidencia.class);
                startActivityForResult(intent,0);

            }
        });

        //ImageButton para Editar perfil
        ImageButton ibEditarPerfil = (ImageButton) findViewById(R.id.ibEditarPerfil);
        ibEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Registro.class);
                startActivityForResult(intent,0);
            }
        });
    }
}
