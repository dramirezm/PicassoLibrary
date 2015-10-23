package tutorial.tabs.diegoramirez.picasso;

/**
 * Created by Diego Ramirez on 05/10/2015.
 */import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GridViewAdapter extends BaseAdapter{

    private Context context;
    private String[] items;

    //Constructor de dos parametros
    public GridViewAdapter(Context context, String[] items){
        super();
        this.context = context;
        this.items = items;
    }

    //Obetenemos la cantidad de imagenes
    @Override
    public int getCount() {
        return items.length;
    }

    //Obtenemos el objeto a partir de su posicion
    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Generamos la vista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Declaramos el ImageView
        ImageView img = null;
        if (convertView == null) {
            //Referenciamos el ImageView
            img = new ImageView(context);
            //Referenciamos el ImageView al convertView
            convertView = img;
            img.setPadding(5, 5, 5, 5);
        } else {
            img = (ImageView) convertView;
        }


        //Uso de la libreria Picasso
        Picasso.with(context)
                //Cargamos la imagen sobre la que se este iterando
                .load(items[position])
                        //Imagen por defecto usada mientras se cargan las imagenes
               // .placeholder(R.drawable.picture)
                .resize(200, 200)
                        //Se aplica sobre la imagen (ImageView - se hizo referencia a "convertView")
                .into(img);

        return convertView;
    }

}