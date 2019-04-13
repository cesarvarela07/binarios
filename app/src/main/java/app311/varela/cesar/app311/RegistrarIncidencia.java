package app311.varela.cesar.app311;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrarIncidencia extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ArrayList<String> provincia =new ArrayList<>();
    ArrayList<String> canton =new ArrayList<>();
    ArrayList<String> distrito =new ArrayList<>();
    ImageView addPhoto = (ImageView) findViewById(R.id.imgPhotoUser);


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            if (data.getExtras()!= null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                addPhoto.setImageBitmap(imageBitmap);
            } else {
                Uri selectedimg = data.getData();
                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg);
                    int width = mBitmap.getWidth();
                    int height = mBitmap.getHeight();
                    float scaleWidth = ((float) 300) / width;
                    float scaleHeight = ((float) 400) / height;
                    // create a matrix for the manipulation
                    Matrix matrix = new Matrix();
                    // resize the bit map
                    matrix.postScale(scaleWidth, scaleHeight);
                    // recreate the new Bitmap
                    Bitmap bit = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
                    addPhoto.setImageBitmap(bit);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_incidencia);

        //SPINNER
        Spinner prov= (Spinner) findViewById(R.id.Provincia);
        Spinner cant= (Spinner) findViewById(R.id.Canton);
        Spinner dist= (Spinner) findViewById(R.id.Distrito);

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

    }




}
