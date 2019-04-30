package app311.varela.cesar.app311;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends BaseAdapter {
    Context context;
    List<NewsPost> newList;

    public Adaptador(Context context, List<NewsPost> newList) {
        this.context = context;
        this.newList = newList;
    }

    @Override
    public int getCount() {
        return newList.size();
    }

    @Override
    public Object getItem(int position) {
        return newList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater i = LayoutInflater.from(context);
        v=i.inflate(R.layout.new_list,null);

        ImageView img = (ImageView)v.findViewById(R.id.img_noticia);
        TextView categoria = (TextView)v.findViewById(R.id.tv_Catego);
        TextView compa = (TextView)v.findViewById(R.id.tv_Compa);
        TextView noticia = (TextView)v.findViewById(R.id.tv_Noticia);

        img.setImageResource(newList.get(position).getImg());
        categoria.setText(newList.get(position).getCategoria());
        compa.setText(newList.get(position).getCompania());
        noticia.setText(newList.get(position).getNoticia());
        return v;
    }
}
