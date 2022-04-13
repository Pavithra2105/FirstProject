package com.example.firstproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListFragment extends Fragment {

    ListView listview2;
    String[] brands = {"Apple","Blackberrry","Moto","Pixel","Samsung","Sony","Vivo","Xiaomi","ReactJS","ONEPLUS"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_list, container, false);

       listview2=view.findViewById(R.id.listview2);
        ArrayAdapter adapter = new ArrayAdapter(inflater.getContext(), android.R.layout.simple_list_item_1,android.R.id.text1,brands);
        listview2.setAdapter(adapter);
       return view;

    }
}