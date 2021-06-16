package com.example.mygrocerylist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;

    public ListViewAdapter(Context context, ArrayList<String> item){
        super(context,R.layout.list_row,item);
        this.context=context;
        list=item;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_row,null);

            TextView name=convertView.findViewById(R.id.name);
            name.setText(list.get(position));

            TextView number=convertView.findViewById(R.id.number);
            number.setText(position+1+".");

            ImageView duplicate = convertView.findViewById(R.id.copy);
            ImageView remove = convertView.findViewById(R.id.remove);

            duplicate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  MainActivity.addItem(list.get(position));
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  MainActivity.removeItem(position);
                }
            });
        }
        return convertView;
    }
}
