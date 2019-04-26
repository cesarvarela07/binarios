package app311.varela.cesar.app311;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrarIncidencia extends AppCompatActivity {
    ArrayList<String> provincia =new ArrayList<>();
    ArrayList<String> canton =new ArrayList<>();
    ArrayList<String> distrito =new ArrayList<>();
    ArrayList<String> empresa =new ArrayList<>();
    ArrayList<String> categoria =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_incidencia);

        //SPINNER
        Spinner prov= (Spinner) findViewById(R.id.Provincia);
        Spinner cant= (Spinner) findViewById(R.id.Canton);
        Spinner dist= (Spinner) findViewById(R.id.Distrito);
        Spinner empr= (Spinner) findViewById(R.id.Empresa);
        Spinner cate= (Spinner) findViewById(R.id.Categoria);

        //FILL DATA
        fillData();

        //ADAPTR
        ArrayAdapter<String> pro=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,provincia);
        prov.setAdapter(pro);

        prov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegistrarIncidencia.this, provincia.get(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //ADAPTR
        ArrayAdapter<String> can=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,canton);
        cant.setAdapter(can);

        cant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegistrarIncidencia.this, canton.get(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //ADAPTR
        ArrayAdapter<String> dis=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,distrito);
        dist.setAdapter(dis);

        dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegistrarIncidencia.this, distrito.get(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

    //FILL DATA
    private void fillData()
    {
        provincia.clear();

        //FILL
        provincia.add("San José");
        provincia.add("Heredia");
        provincia.add("Cartago");
        provincia.add("Alajuela");
        provincia.add("Limón");
        provincia.add("Punturenas");
        provincia.add("Guanacaste");


        canton.clear();

        //FILL
        canton.add("San José");
        canton.add("Desamparados");
        canton.add("Escazú");



        distrito.clear();

        //FILL
        distrito.add("San José");
        distrito.add("Desamparados");
        distrito.add("Escazú");

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

//        // Here, we are making a folder named picFolder to store
//        // pics taken by the camera using this application.
//        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
//        File newdir = new File(dir);
//        newdir.mkdirs();
//
//        Button capture = (Button) findViewById(R.id.btnCapture);
//        capture.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                // Here, the counter will be incremented each time, and the
//                // picture taken by camera will be stored as 1.jpg,2.jpg
//                // and likewise.
//                count++;
//                String file = dir+count+".jpg";
//                File newfile = new File(file);
//                try {
//                    newfile.createNewFile();
//                }
//                catch (IOException e)
//                {
//                }
//
//                Uri outputFileUri = Uri.fromFile(newfile);
//
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//
//                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
//            Log.d("CameraDemo", "Pic saved");
//        }
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        try {
//
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode != 0 && data != null) {
//
//                if (data.getExtras()!= null) {
//                    Bundle extras = data.getExtras();
//                    Bitmap imageBitmap = (Bitmap) extras.get("data");
//                    addPhoto.setImageBitmap(imageBitmap);
//                } else {
//                    Uri selectedimg = data.getData();
//                    try {
//                        Bitmap mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg);
//                        int width = mBitmap.getWidth();
//                        int height = mBitmap.getHeight();
//                        float scaleWidth = ((float) 300) / width;
//                        float scaleHeight = ((float) 400) / height;
//                        // create a matrix for the manipulation
//                        Matrix matrix = new Matrix();
//                        // resize the bit map
//                        matrix.postScale(scaleWidth, scaleHeight);
//                        // recreate the new Bitmap
//                        Bitmap bit = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
//                        addPhoto.setImageBitmap(bit);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        }catch(Exception e){
//
//        }
//    }
