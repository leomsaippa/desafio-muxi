package com.projeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/*
Foi usada a biblioteca volley para fazer o consumo do Json
Informações: https://developer.android.com/training/volley/index.html

 */
public class ListClass extends AppCompatActivity{

    //Json que será consumido
    String JsonURL = "https://raw.githubusercontent.com/muxidev/desafio-android/master/fruits.json";
    // Fila de requisiçao
    RequestQueue requestQueue;
    ListView listView;
    String name;
    String image;
    String price;
    ArrayList<Fruit> fruitArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_class);
        listView = (ListView) findViewById(R.id.listViewID);
        //Inicializa a fila de requisição
        requestQueue = Volley.newRequestQueue(this);
        //Função para consumir o Json e salvar numa listView
        getJson();
        //Verifica o  clique de cada elemento da lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int ia, long l) {
                proximaTela(ia,fruitArrayList);

            }
        });
    }

    private void getJson(){
        //Faz uma nova requisição
        JsonObjectRequest arrayreq = new JsonObjectRequest(Request.Method.GET,JsonURL,
                //O segundo parametro sobrescreve o método onResponse() passando o JSONArray como parametro
                new Response.Listener<JSONObject>() {

                    // Recebe a "resposta" do JSON
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //Identifica o jsonArray
                            JSONArray jsonArray = response.getJSONArray("fruits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //Pega cada objeto do jsonArray e salva no ArrayList
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                name = jsonObject.getString("name");
                                image = jsonObject.getString("image");
                                price = jsonObject.getString("price");
                                Fruit fruta = new Fruit(name, image, price);
                                fruitArrayList.add(fruta);
                            }
                            //Adapter criado para mostrar 2 informações na mesma linha do ListView, usando Singleton
                            FruitListAdapter adapter = FruitListAdapter.getInstance(getApplicationContext(), R.layout.adapter_view_layout, fruitArrayList);
                            listView.setAdapter(adapter);
                        }
                        catch (JSONException e) {
                            // Se der algum erro, apenas informa
                            e.printStackTrace();
                        }
                    }
                },
                //Ultimo parametro sobrescreve metodo onErrorResponse passando o VolleyError como parametro
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        //Adiciona a requisição a fila
        requestQueue.add(arrayreq);
    }


    //Passa para a proxima tela com a informação de qual item foi clicado e a lista de frutas
    public void proximaTela(int posicao, ArrayList<Fruit> fruitArrayList){
        Bundle bundle = new Bundle();
        bundle.putString("ID",""+posicao);
        Intent i = new Intent(ListClass.this, ImageClass.class);
        i.putExtra("array", fruitArrayList);
        i.putExtras(bundle);
        startActivity(i);

    }



}
