package com.projeto;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
/*
Autor: Leonardo Muniz Saippa
Julho de 2017

*/
public class MainActivity extends AppCompatActivity {

    Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Verifica se a versao do android suporta o efeito de transicao(API > 20)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        } else {}
        //Identifica se o botão foi clicado
        setContentView(R.layout.activity_main);
        botao = (Button) findViewById(R.id.buttonID);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proximaTela();
            }
        });

    }


    public void proximaTela(){
        //Faz a transicao de tela, testando a API>20
        Intent i = new Intent(MainActivity.this, ListClass.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
            startActivity(i,ActivityOptions
                    .makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(i);
        }

    }

}
