package com.example.leiturajson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
       { "nome":"parafuso","preco":"100"}
        */
    String jsonsimples = "{ \"nome\":\"parafuso\",\"preco\":\"100\"}";
    TextView resultado1,resultado2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado1 = (TextView)findViewById(R.id.resultado);
        resultado2 = (TextView)findViewById(R.id.resultado2);
        try{
            //Criamos um objeto Json usando a string
            JSONObject json = new JSONObject(jsonsimples);
            String nome=json.optString("nome").toString();
            String preco=json.optString("preco").toString();
            resultado1.setText("Nome : "+nome+" Preço : "+preco);
        }catch (Exception ex){
        }
        // array de objetos
     /*
        { "loja":[
        {"nome":"mouse"=,"preco":"15.0"},
        {"nome":"teclado","preco":"45.0"}
        ]}
         */
        String vetorjsonobjeto = "{ \"loja\":[{\"nome\":\"mouse\",\"preco\":\"15.0\"},"+
                "{\"nome\":\"teclado\",\"preco\":\"45.0\"}]}";
        try{
            JSONObject jsonobjc = new JSONObject(vetorjsonobjeto);
            JSONArray jsonvet = jsonobjc.getJSONArray("loja");
            String txtaux="";
            for(int i=0;i<jsonvet.length();i++){
                JSONObject jsonitem=jsonvet.getJSONObject(i);
                String nome=jsonitem.optString("nome").toString();
                String preco=jsonitem.optString("preco").toString();
                txtaux+="Nome : "+nome+" Preço : "+preco+"\n";
            }
            resultado2.setText(txtaux);
        }catch (Exception ex){
        }
        ListView listView = (ListView) findViewById(R.id.list_view);
        try {
            List<String> items = new ArrayList<>();
            JSONObject jsonobetos = new JSONObject(vetorjsonobjeto);
            JSONArray vetor = jsonobetos.getJSONArray("loja");
            for(int i=0;i<vetor.length();i++)
            {
                JSONObject object= vetor.getJSONObject(i);
                items.add("Produto"+object.getString("nome")+" Preço : "+object.getString("preco"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, items);

            if (listView != null) {
                listView.setAdapter(adapter);
            }

        }catch(Exception ex){


        }

    }
}