package com.projeto;

import android.content.Context;
import org.junit.Test;


import static org.junit.Assert.*;

public class ExampleUnitTest extends NativeConvert{
    public ExampleUnitTest(Context context, NativeInterface nativeInterface) {
        super(context, nativeInterface);
    }



    //Verifica se está calculando a conversão de forma correta. Testa 50x o calculo
    @Test
    public void doInBackground_isCorrect() throws Exception{
        for (int i=1; i < 50;i++){
            String valor = ""+i;
            float resultado = (float) (Float.parseFloat(valor) * 3.5);
            assertEquals(doInBackground(valor),""+resultado);
        }

    }




}