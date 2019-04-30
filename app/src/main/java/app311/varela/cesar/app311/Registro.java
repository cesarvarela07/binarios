package app311.varela.cesar.app311;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private Spinner txtProvincia;
    private Spinner txtCanton;
    private Spinner txtDistrito;


    Random rand = new Random();

    String[] provincias = {"San José","Alajuela","Cartago","Heredia","Guanacaste","Puntarenas","Limón"};
    String[] cantones = {""};
    String[] distritos = {"Distrito 1","Distrito 2","Distrito 3","Distrito 4","Distrito 5","Distrito 6","Distrito 7","Distrito 8","Distrito 9","Distrito 10"};

    String[] distritosSJ = {"Central","Escazú","Desamparados","Puriscal","Tarrazú","Aserrí","Mora","Goicoechea","Santa Ana","Alajuelita","Vázquez De Coronado","Acosta","Tibás","Moravia","Montes De Oca","Turrubares","Dota","Curridabat","Pérez Zeledón","León Cortés Castro"};
    String[] distritosAJ = {"Central","San Ramón","Grecia","San Mateo","Atenas","Naranjo","Palmares","Poás","Orotina","San Carlos","Zarcero","Valverde Vega","Upala","Los Chiles","Guatuso","Río Cuarto"};
    String[] distritosCA = {"Central","Paraíso","La Unión","Jiménez","Turrialba","Alvarado","Oreamuno","El Guarco"};
    String[] distritosHE = {"Central","Barva","Santo Domingo","Santa Barbara","San Rafael","San Isidro","Belén","Flores","San Pablo","Sarapiquí"};
    String[] distritosGU = {"Liberia","Nicoya","Santa Cruz","Bagaces","Carrillo","Cañas","Abangares","Tilarán","Nandayure","La Cruz","Hojancha"};
    String[] distritosPU = {"Central","Esparza","Buenos Aires","Montes De Oro","Osa","Quepos","Golfito","Coto Brus","Parrita","Corredores","Garabito"};
    String[] distritosLI = {"Central","Pococí","Siquirres","Talamanca","Matina","Guácimo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mDatabaseHelper = new DatabaseHelper(this);
        agregarColumnas();
        obtenerDatosDeActivity();

        Spinner spProvincias = (Spinner)findViewById(R.id.Provincia);
        final Spinner spCanton = (Spinner)findViewById(R.id.Canton);
        Spinner spDistrito = (Spinner)findViewById(R.id.Distrito);

        spProvincias.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,provincias));
        spCanton.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,cantones));
        spDistrito.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,distritos));

        Button btnSave = (Button)  findViewById(R.id.btnSave);
        //0 = registro primera vez
        //1 = editar perfil
        if(1 == getIntent().getExtras().getInt("MODE"))
        {
            btnSave.setText("Guardar Edición");
            obtenerDatosDeBD();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarListaParametros();
                if(0 == getIntent().getExtras().getInt("MODE"))
                    addData();
                else
                    updateData();
            }
        });

        spProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                    spCanton.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,distritosSJ));
                if(i == 1)
                    spCanton.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,distritosAJ));
                if(i == 2)
                    spCanton.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,distritosCA));
                if(i == 3)
                    spCanton.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,distritosHE));
                if(i == 4)
                    spCanton.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,distritosGU));
                if(i == 5)
                    spCanton.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,distritosPU));
                if(i == 6)
                    spCanton.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,distritosLI));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void addData()
    {
        String tableName = ConfiguracionSQLite.TB_NAME_REGISTRO_USUARIO;

        if(Validador.email(listaParametros.get(7)))
        {
            //columnas
            boolean insertData = mDatabaseHelper.addData(listaParametros,listaColumnas,tableName);
            if (insertData)
                MetodosGlobales.toastMessage(this,"Insertado correctamente");
            else
                MetodosGlobales.toastMessage(this,"Error al insertar los datos");
        }else
        {
            MetodosGlobales.toastMessage(this,"Formato de correo incorrecto");
        }


    }

    public void updateData()
    {
        String tableName = ConfiguracionSQLite.TB_NAME_REGISTRO_USUARIO;
        //columnas
        boolean updateData = mDatabaseHelper.updateData(listaParametros,listaColumnas,tableName,MetodosGlobales.CEDULA_USUARIO_ACTIVO,ConfiguracionSQLite.TB_RI_CEDULA);
        if (updateData)
            MetodosGlobales.toastMessage(this,"Actualizado correctamente");
        else
            MetodosGlobales.toastMessage(this,"Error al actualizar los datos");
    }

    public void agregarColumnas()
    {
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
    }

    public void obtenerDatosDeActivity()
    {
        txtCedula = (EditText) findViewById(R.id.cedulaTxt);
        txtNombre = (EditText) findViewById(R.id.nombreTxt);
        txtApellido1 = (EditText) findViewById(R.id.apellido1Txt);
        txtApellido2 = (EditText) findViewById(R.id.apellido2Txt);
        txtTelefono = (EditText) findViewById(R.id.telefonoTxt);
        txtEmail = (EditText)findViewById(R.id.emailTxt);
        txtPassword = (EditText)findViewById(R.id.passwordTxt);
        txtDireccion = (EditText)findViewById(R.id.direccionTxt);
        txtProvincia = (Spinner)findViewById(R.id.Provincia);
        txtCanton = (Spinner)findViewById(R.id.Canton);
        txtDistrito = (Spinner)findViewById(R.id.Distrito);

    }
    public void obtenerDatosDeBD()
    {
        listaValores = mDatabaseHelper.getValues(ConfiguracionSQLite.TB_NAME_REGISTRO_USUARIO,"CEDULA",MetodosGlobales.CEDULA_USUARIO_ACTIVO,listaColumnas);
        txtCedula.setText(listaValores.get(0));
        txtNombre.setText(listaValores.get(1));
        txtApellido1.setText(listaValores.get(2));
        txtApellido2.setText(listaValores.get(3));

        txtEmail.setText(listaValores.get(7));
        txtTelefono.setText(listaValores.get(8));
        txtPassword.setText(listaValores.get(9));
        txtDireccion.setText(listaValores.get(12));

    }

    public void cargarListaParametros()
    {
        listaParametros.add(txtCedula.getText().toString());
        listaParametros.add(txtNombre.getText().toString());
        listaParametros.add(txtApellido1.getText().toString());
        listaParametros.add(txtApellido2.getText().toString());

        listaParametros.add(txtProvincia.getSelectedItem().toString());
        listaParametros.add(txtCanton.getSelectedItem().toString());
        listaParametros.add(txtDistrito.getSelectedItem().toString());

        listaParametros.add(txtEmail.getText().toString());
        listaParametros.add(txtTelefono.getText().toString());
        listaParametros.add(txtPassword.getText().toString());

        listaParametros.add(""+rand.nextInt(100 - 1 + 1) + 1);
        listaParametros.add("I");
        listaParametros.add(txtDireccion.getText().toString());
    }

}
