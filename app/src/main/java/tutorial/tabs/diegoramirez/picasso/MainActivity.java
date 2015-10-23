package tutorial.tabs.diegoramirez.picasso;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends Activity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    private String[] items;

    //Array de String que contiene las URL de las imagenes externas que usemos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos la referencia de la vista
        gridView = (GridView) findViewById(R.id.gridView);

        ObtenerDatos();

    }


    public void ObtenerDatos()
    {
        AsyncHttpClient cliente = new AsyncHttpClient();
        String URL = "http://pruebadiegoimagenes.site40.net/DownloadImages.php";

        RequestParams parametros = new RequestParams();
        parametros.put("Edad",18);
        parametros.put("Nombre","Diego");



        cliente.post(URL, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    //cargaLista(DatosJson(new String(responseBody)));
                    Toast.makeText(getApplicationContext(), "Entro ", Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(new String(responseBody));
                        int tamano = jsonArray.length();
                        String[] listado = new String[tamano];

                        Log.e("JSONArray",""+jsonArray);
                        Toast.makeText(getApplicationContext(),""+jsonArray,Toast.LENGTH_LONG).show();
                        //String hey = jsonArray.toString();
                       // Toast.makeText(getApplicationContext(),""+hey,Toast.LENGTH_LONG).show();
                        for (int i = 0 ; i < jsonArray.length(); i++)
                        {

                            listado[i] = jsonArray.getJSONObject(i).getString("Imagen");

                            Log.e("listado " , ""+listado);

                        }




                        gridAdapter = new GridViewAdapter(MainActivity.this, listado);

                        //Especificamos el adaptador
                        gridView.setAdapter(gridAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"aqui",Toast.LENGTH_SHORT).show();
                    }






                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}