package app311.varela.cesar.app311;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Registro extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    ArrayList<String> listaParametros = new ArrayList<String>();
    ArrayList<String> listaColumnas = new ArrayList<String>();
    ArrayList<String> listaValores = new ArrayList<String>();
    private EditText txtCedula;
    private EditText txtNombre;
    private EditText txtApellido1;
    private EditText txtApellido2;
    private EditText txtTelefono;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtDireccion;

    Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mDatabaseHelper = new DatabaseHelper(this);

        listaColumnas.add(ConfiguracionSQLite.TB_RU_CEDULA);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_NOMBRE);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_APELLIDO1);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_APELLIDO2);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_PROVINCIA);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_CANTON);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_DISTRITO);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_CORREO);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_CELULAR);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_PASSWORD);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_COD_ACTIVACION);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_ESTADO);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_DIRECCION);

        Button btnSave = (Button)  findViewById(R.id.btnSave);
        txtCedula = (EditText) findViewById(R.id.cedulaTxt);
        txtNombre = (EditText) findViewById(R.id.nombreTxt);
        txtApellido1 = (EditText) findViewById(R.id.apellido1Txt);
        txtApellido2 = (EditText) findViewById(R.id.apellido2Txt);
        txtTelefono = (EditText) findViewById(R.id.telefonoTxt);
        txtEmail = (EditText)findViewById(R.id.emailTxt);
        txtPassword = (EditText)findViewById(R.id.passwordTxt);
        txtDireccion = (EditText)findViewById(R.id.direccionTxt);

        if(1 == getIntent().getExtras().getInt("MODE"))
        {
            listaValores = mDatabaseHelper.getValues(ConfiguracionSQLite.TB_NAME_REGISTRO_USUARIO,"117010413",listaColumnas);
            txtNombre.setText(listaValores.get(1));
            txtApellido2.setText(listaValores.get(3));
            txtPassword.setText(listaValores.get(9));

        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listaParametros.add(txtCedula.getText().toString());
                listaParametros.add(txtNombre.getText().toString());
                listaParametros.add(txtApellido1.getText().toString());
                listaParametros.add(txtApellido2.getText().toString());

                listaParametros.add("");
                listaParametros.add("");
                listaParametros.add("");

                listaParametros.add(txtEmail.getText().toString());
                listaParametros.add(txtTelefono.getText().toString());
                listaParametros.add(txtPassword.getText().toString());

                listaParametros.add(""+rand.nextInt(100 - 1 + 1) + 1);
                listaParametros.add("I");


                listaParametros.add(txtDireccion.getText().toString());

                addData();
            }
        });
    }


    public void addData()
    {

        String tableName = ConfiguracionSQLite.TB_NAME_REGISTRO_USUARIO;
        //columnas

        /*listaColumnas.add(ConfiguracionSQLite.TB_RU_CEDULA);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_NOMBRE);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_APELLIDO1);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_APELLIDO2);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_PROVINCIA);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_CANTON);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_DISTRITO);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_CORREO);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_CELULAR);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_PASSWORD);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_COD_ACTIVACION);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_ESTADO);
        listaColumnas.add(ConfiguracionSQLite.TB_RU_DIRECCION);*/


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
