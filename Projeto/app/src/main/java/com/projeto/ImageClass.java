package com.projeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ImageClass extends AppCompatActivity implements NativeInterface{

    TextView textViewTitulo, textViewPrecoDolar, textViewPrecoReal;
    String id;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_class);
        imageView = (ImageView) findViewById(R.id.imageViewID);
        //Recupera as informações passadas pela tela anterior
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("ID");
        ArrayList<Fruit> fruitArrayList = (ArrayList<Fruit>) getIntent().getSerializableExtra("array");
        //Salva o id do objeto clicado
        int identificador = Integer.parseInt(id);
        float valor = Float.parseFloat(fruitArrayList.get(identificador).getPrice());
        //Coloca o preco em dolar no formato de 2 casas decimais
        BigDecimal result;
        result = round(valor,2);
        String name = fruitArrayList.get(identificador).getName();
        //Chama o método assincrono para calcular o valor em real
        chamarAsyncTask(result.floatValue());
        //Pega o URL da imagem
        final String imagem =  fruitArrayList.get(identificador).getImage();
        //Coloca as informações nos TextView para serem exibidos
        textViewTitulo = (TextView) findViewById(R.id.textViewTituloID);
        textViewPrecoDolar = (TextView) findViewById(R.id.textViewPrecoDolarID);
        textViewTitulo.setText(name);
        textViewPrecoDolar.setText("US$ "+result);
        /*  Usa a biblioteca Picaso para salvar a imagem no imageView
            A biblioteca já tem um pequeno mecanismo de cache
            Mais info: http://square.github.io/picasso/
        */
       Picasso.with(getApplicationContext()).load(imagem).into(imageView);



        /*

        Este trecho de código era utilizado para melhorar o uso da cache, porém
        não se mostrou muito eficiente quando tinha problemas contínuos de conexão a internet

        Picasso.with(getApplicationContext())
                .load(imagem)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.v("Picaso", "Imagem carregada corretamente");
                    }

                    @Override
                    public void onError() {
                        Picasso.with(getApplicationContext()).load(imagem).error(0).into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.v("Picaso", "Imagem carregada corretamente");
                            }

                            @Override
                            public void onError() {
                                Log.v("Picaso", "Imagem não pode ser carregada");
                            }
                        });
                    }
                });

           */
    }

    //Metodo para definir as casas decimais
    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public void chamarAsyncTask(float valor){
        NativeConvert nativeConvert = new NativeConvert(this,this);
        nativeConvert.execute(""+valor);
    }


    @Override
    public void depoisCalculo(Float resultado) {

        textViewPrecoReal = (TextView) findViewById(R.id.textViewPrecoRealID);
        //Coloca um zero em valores que terminavam em 1 casa decimal (Ex: 12,5 -> 12,50)
        BigDecimal result;
        result = round(resultado,2);
        textViewPrecoReal.setText("R$ "+result);

    }
}
