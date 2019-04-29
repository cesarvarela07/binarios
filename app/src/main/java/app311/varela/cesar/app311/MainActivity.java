package app311.varela.cesar.app311;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHelper = new DatabaseHelper(this);

        //Button para Menu Principal
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtEmail = (EditText)findViewById(R.id.emailTxt);

                if("Y".equalsIgnoreCase(mDatabaseHelper.existeCorreo(ConfiguracionSQLite.TB_NAME_REGISTRO_USUARIO,txtEmail.getText().toString())))
                {
                    MetodosGlobales.CEDULA_USUARIO_ACTIVO = mDatabaseHelper.obtenerIdCedulaUusario(ConfiguracionSQLite.TB_NAME_REGISTRO_USUARIO,txtEmail.getText().toString());
                    Intent intent = new Intent(v.getContext(),Menu.class);
                    startActivityForResult(intent,0);
                }
                else
                {
                    MetodosGlobales.toastMessage(MainActivity.this,"Correo incorrecto");
                }





            }
        });


        Button btnRegistro = (Button)findViewById(R.id.btnRegistrarse);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Registro.class);
                intent.putExtra("MODE",0);
                startActivityForResult(intent,0);

            }
        });
    }
}
