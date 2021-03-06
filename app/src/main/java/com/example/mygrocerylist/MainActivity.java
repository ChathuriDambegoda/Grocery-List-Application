package com.example.mygrocerylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ListView listView;
    static ArrayList<String> items;
    static ListViewAdapter adapter;
    EditText input;
    ImageView enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
        input=findViewById(R.id.input);
        enter=findViewById(R.id.add);
        items = new ArrayList<>();
        items.add("Apple");
        items.add("Banana");
        items.add("Orange");
        items.add("Strawberry");
        items.add("Kiwi");
        items.add("Mango");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     String name=items.get(position);
                     makeToast(name);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                makeToast("Removed: "+items.get(position));
                removeItem(position);
                return false;
            }
        });

        adapter=new ListViewAdapter(getApplicationContext(),items);
        listView.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=input.getText().toString();
                if(text==null || text.length() == 0){
                    makeToast("Enter an item.");
                }else{
                    addItem(text);
                    input.setText(" ");
                    makeToast("Added : "+text);
                }
            }
        });


    }
    public static void removeItem(int remove){
        items.remove(remove);
        adapter.notifyDataSetChanged();

    }

    public static void addItem(String item){
        items.add(item);
       // listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    Toast t;

    private void makeToast(String s){
        if(t != null) t.cancel();
        t=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        t.show();
    }
}