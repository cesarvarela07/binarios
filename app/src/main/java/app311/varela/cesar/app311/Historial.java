package app311.varela.cesar.app311;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Historial extends AppCompatActivity {

    private ListView newsList;
    private ArrayList<NewsPost>list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        newsList=(ListView)findViewById(R.id.lv_ListaNoticias);
        newsList=(ListView)findViewById(R.id.lv_ListaNoticias);

        list= new ArrayList<NewsPost>();
        list.add(new NewsPost("Falta de Agua","Servicios Publicos","A y A",R.drawable.new1));
        list.add(new NewsPost("Falta de Agua","Servicios Publicos","A y A",R.drawable.new1));
        list.add(new NewsPost("Falta de Agua","Servicios Publicos","A y A",R.drawable.new1));
        list.add(new NewsPost("Falta de Agua","Servicios Publicos","A y A",R.drawable.new1));
        list.add(new NewsPost("Falta de Agua","Servicios Publicos","A y A",R.drawable.new1));

        Adaptador adaptador= new Adaptador(getApplicationContext(),list);
        newsList.setAdapter(adaptador);

        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsPost valor= list.get(position);
                Toast msj=Toast.makeText(getApplicationContext(),"Item Selecionado"+" Item "+ valor.getCategoria(),Toast.LENGTH_LONG);
                msj.show();
            }
        });

    }
}
