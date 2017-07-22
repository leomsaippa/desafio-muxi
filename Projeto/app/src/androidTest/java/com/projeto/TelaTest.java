package com.projeto;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class TelaTest {
    //Vetor com o nome das 12 frutas, apenas para testes
    String frutas[] = {"Apple" , "Banana", "Grapes","Pineapple","cherry","clementine","olive","tomato","huckleberry","papaya","lime","pear"};

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void changeText_newActivity() throws Exception {
        // Verifica se o nome do botão está correto e clica.
        onView(withId(R.id.buttonID)).perform(click());
        //Espera 3 segundos para continuar os testes, afim de evitar problemas de conexão
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Na segunta tela, verifica se o titulo é "Frutas"
        onView(withId(R.id.textViewID)).check(matches(withText("Frutas")));
        //Percorre o listView clicando em cada um dos elementos e verificando se o seu nome está correto
         for(int i =0; i<12 ;i++){
            String nome = frutas[i];
            onData(anything())
                    .inAdapterView(withId(R.id.listViewID   )).atPosition(i)
                    .perform(click());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onView(withId(R.id.textViewTituloID)).check(matches(withText(nome)));
            pressBack();
        }

    }

}
