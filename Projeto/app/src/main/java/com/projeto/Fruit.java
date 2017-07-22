package com.projeto;

import java.io.Serializable;


public class Fruit implements Serializable{

    //Apenas uma classe para salvar as informações das frutas
    private String name;
    private String image;
    private String price;


    public Fruit(String name, String image, String price){
        this.name=name;
        this.image=image;
        this.price=price;

    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }

    public String getPrice(){
        return price;
    }


}
