package app311.varela.cesar.app311;

import android.graphics.Bitmap;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;



import java.util.ArrayList;

public class RegistrarIncidencia extends AppCompatActivity {
    ArrayList<String> empresa =new ArrayList<>();
    ArrayList<String> categoria =new ArrayList<>();

    DatabaseHelper mDatabaseHelper;
    ArrayList<String> listaParametros = new ArrayList<String>();
    ArrayList<String> listaColumnas = new ArrayList<String>();

    ImageView img;
    ImageButton btn;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    LocationManager locationManager;

    double longitudeGPS, latitudeGPS;
    TextView longitudeValueGPS, latitudeValueGPS;

    private Spinner txtEmpresa;
    private Spinner txtCategoria;
    private TextView txtDireccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_incidencia);

        mDatabaseHelper = new DatabaseHelper(this);
        agregarColumnas();
        obtenerDatosDeActivity();

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


        longitudeValueGPS = (TextView) findViewById(R.id.longitudeValueGPS);
        latitudeValueGPS = (TextView) findViewById(R.id.latitudeValueGPS);

        btn=(ImageButton) findViewById(R.id.btnCapture);
        img=(ImageView) findViewById(R.id.imgPost);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        Button btnIncide = (Button) findViewById(R.id.btnIncide);

        btnIncide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarListaParametros();
                addData();
            }
        });


        //SPINNER
        Spinner empr= (Spinner) findViewById(R.id.Empresa);
        Spinner cate= (Spinner) findViewById(R.id.Categoria);

        //FILL DATA
        fillData();

        //ADAPTR
        ArrayAdapter<String> emp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,empresa);
        empr.setAdapter(emp);

        empr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegistrarIncidencia.this, empresa.get(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //ADAPTR
        ArrayAdapter<String> cat=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categoria);
        cate.setAdapter(cat);

        cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegistrarIncidencia.this, categoria.get(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Su ubicación esta desactivada.\npor favor active su ubicación " +
                        "usa esta app")
                .setPositiveButton("Configuración de ubicación", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void toggleGPSUpdates(View view) {
        if (!checkLocation())
            return;
        Button button = (Button) view;
        if (button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerGPS);
            button.setText(R.string.resume);
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 2 * 20 * 1000, 10, locationListenerGPS);
            button.setText(R.string.pause);
        }
    }



    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueGPS.setText(longitudeGPS + "");
                    latitudeValueGPS.setText(latitudeGPS + "");
                    Toast.makeText(RegistrarIncidencia.this, "GPS Provider update", Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }
        @Override
        public void onProviderDisabled(String s) {
        }
    };


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
        }
    }



    public void addData()
    {
        String tableName = ConfiguracionSQLite.TB_NAME_REGISTRO_INCIDENCIA;

        //columnas
        boolean insertData = mDatabaseHelper.addData(listaParametros,listaColumnas,tableName);
        if (insertData)
            MetodosGlobales.toastMessage(this,"Insertado correctamente");
        else
            MetodosGlobales.toastMessage(this,"Error al insertar los datos");


    }
/*
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
*/
    public void agregarColumnas()
    {
        listaColumnas.add(ConfiguracionSQLite.TB_RI_CEDULA);
        listaColumnas.add(ConfiguracionSQLite.TB_RI_CATEGORIA);
        listaColumnas.add(ConfiguracionSQLite.TB_RI_EMPRESA);
        listaColumnas.add(ConfiguracionSQLite.TB_RI_DIRECCION);
        listaColumnas.add(ConfiguracionSQLite.TB_RI_ESTADO);
    }

    public void obtenerDatosDeActivity()
    {
        txtEmpresa = (Spinner) findViewById(R.id.Empresa);
        txtCategoria = (Spinner) findViewById(R.id.Categoria);
        txtDireccion = (EditText)findViewById(R.id.lblDireccion);
    }
/*
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
    */

    public void cargarListaParametros()
    {
        listaParametros.add(MetodosGlobales.CEDULA_USUARIO_ACTIVO);
        listaParametros.add(txtEmpresa.getSelectedItem().toString());
        listaParametros.add(txtCategoria.getSelectedItem().toString());
        listaParametros.add(txtDireccion.getText().toString());
        listaParametros.add("I");
    }


    //FILL DATA
    private void fillData()
    {

        empresa.clear();

        //FILL
        empresa.add("AyA");
        empresa.add("ICE");
        empresa.add("Municipalidad");
        empresa.add("Cablera");



        categoria.clear();

        //FILL
        categoria.add("Puentes");
        categoria.add("Carreteras");
        categoria.add("Servicios públicos");
        categoria.add("Servicio privado");

    }


}
