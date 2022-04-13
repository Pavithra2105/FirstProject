 package com.example.firstproject;

  import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

 public class CustomGridAdapter extends ArrayAdapter<String> {
    private final Context  context;
    private final String[] brands;
    private final Integer[] logos;

    public CustomGridAdapter (Context context,String[] brands,Integer[] logos){
        super(context,R.layout.grid_item, brands);
        this.context = context;
        this.brands = brands;
        this.logos = logos;
    }

    @NonNull

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext(). getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.grid_item, null,true);

        ImageView brandimage = view.findViewById(R.id.image);
        TextView logotext = view.findViewById(R.id.logo_text);

        logotext.setText(brands[position]);
        brandimage.setImageResource(logos[position]);
        return view;
    }
}
