package app311.varela.cesar.app311;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    ArrayList<String> listaParametros = new ArrayList<String>();;
    ArrayList<String> listaColumnas = new ArrayList<String>();;
    private EditText txtCedula;
    private EditText txtNombre;
    private EditText txtTelefono;
    private EditText txtEmail;
    private EditText txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mDatabaseHelper = new DatabaseHelper(this);
        Button btnSave = (Button)  findViewById(R.id.btnSave);
        txtCedula = (EditText) findViewById(R.id.cedulaTxt);
        txtNombre = (EditText) findViewById(R.id.nombreTxt);
        txtTelefono = (EditText) findViewById(R.id.telefonoTxt);
        txtEmail = (EditText)findViewById(R.id.emailTxt);
        txtPassword = (EditText)findViewById(R.id.passwordTxt);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listaParametros.add(txtCedula.getText().toString());
                listaParametros.add(txtNombre.getText().toString());
                listaParametros.add(txtEmail.getText().toString());
                listaParametros.add(txtTelefono.getText().toString());
                listaParametros.add(txtPassword.getText().toString());


                addData();
            }
        });
    }


    public void addData()
    {

        String tableName = "USUARIO";
        //columnas
        listaColumnas.add("CEDULA");
        listaColumnas.add("NOMBRE");
        listaColumnas.add("EMAIL");
        listaColumnas.add("TELEFONO");
        listaColumnas.add("PASSWORD");
        boolean insertData = mDatabaseHelper.addData(listaParametros,listaColumnas,tableName);
        if (insertData)
            toastMessage("Insertado correctamente");
        else
            toastMessage("Error al insertar los datos");
    }
    private void toastMessage(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
}
