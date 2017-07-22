package com.projeto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FruitListAdapter extends ArrayAdapter<Fruit> {

    private static FruitListAdapter instance = null ;
    private Context mContext;
    int mResource;


    public FruitListAdapter(Context context, int resource, ArrayList<Fruit> objects){
        super(context,resource,objects);
        mContext = context;
        mResource = resource;

    }

    //Singleton
    public static FruitListAdapter getInstance(Context context,int resource, ArrayList<Fruit> objects){
        if(instance == null){
            instance = new FruitListAdapter(context,resource,objects);
        }
        return instance;

    }

    //Adapta a ListView para receber duas colunas (nome e preço)
    public View getView(int position, View convertView, ViewGroup parent){
        String name = getItem(position).getName();
        String price = getItem(position).getPrice();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView textViewName = (TextView) convertView.findViewById(R.id.textNameID);
        TextView textViewPrice = (TextView) convertView.findViewById(R.id.textPriceID);
        textViewName.setText(name);
        textViewPrice.setText("US$ "+price);

        return convertView;
    }

}
