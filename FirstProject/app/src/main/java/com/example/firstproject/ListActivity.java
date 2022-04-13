package com.example.firstproject;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firstproject.R;
public class ListActivity extends AppCompatActivity {
    ListView listview;
    String[] brands = {"Apple","Blackberrry","Moto","Pixel","Samsung","Sony","Vivo","Xiaomi","ReactJS","ONEPLUS"};
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //set ids
        listview = findViewById(R.id.list2);
        //adapter
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,brands);
        //apply adapter to listView
        listview.setAdapter(adapter);

        listview.setAdapter(adapter);

       listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               String itemValue  =listview.getItemAtPosition(i).toString();
               switch(itemValue){
                   case "Apple" :
                       Toast.makeText(ListActivity.this, "Sales..!", Toast.LENGTH_SHORT).show();
                       break;
                   case "Xiaomi":
                           Toast.makeText(ListActivity.this, "Xiamo!1", Toast.LENGTH_SHORT).show();
                           break;
                   case "Pixel":
                       Toast.makeText(ListActivity.this, "Hi..", Toast.LENGTH_SHORT).show();
                       break;
               }
           }
       });
    }
}


