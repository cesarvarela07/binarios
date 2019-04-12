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
