package com.projeto;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class NativeConvert extends AsyncTask<String,String,String> {

    private Context context;
    private ProgressDialog progress;
    private NativeInterface nativeInterface;

    //Metodo nativo para calculo do preco em real
    public native String calcular(float num);
    static {
        System.loadLibrary("calculator");
    }

    public NativeConvert(Context context, NativeInterface nativeInterface){
        this.context = context;
        this.nativeInterface = nativeInterface;
    }

    //Enquanto é executado o calculo uma barra de progresso exibe a mensagem fazendo calculo
    @Override
    protected void onPreExecute(){
        progress = new ProgressDialog(context);
        progress.setMessage("Fazendo o cálculo!");
        progress.show();
    }

    //De forma assincrona calcula o preço com metodo nativo "calcular"
    protected String doInBackground(String... params){
        Float valor = Float.parseFloat(params[0]);
        publishProgress("Calculando");
        String retorno = calcular(valor);
        return retorno;
    }
    @Override
    protected void onProgressUpdate(String...  params){
        progress.setMessage(params[0]);
    }

    //Apos a execução chama o método que da andamento ao aplicativo
    protected void onPostExecute(String params){
        nativeInterface.depoisCalculo(Float.parseFloat(params));
        progress.dismiss();

    }

}
