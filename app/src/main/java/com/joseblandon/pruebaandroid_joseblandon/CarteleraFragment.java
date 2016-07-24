package com.joseblandon.pruebaandroid_joseblandon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarteleraFragment extends Fragment {


    public CarteleraFragment() {
        // Required empty public constructor
    }

final String[] peliculas = new String[] {"Toy Story", "Rocky Balboa","Inception","John Wick"};
ListView lstCartelera;
    private ListView listView;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cartelera, container, false);
        listView = (ListView) view.findViewById(R.id.lstCartelera);

        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,peliculas));

        //lstCartelera = (ListView) view.findViewById(R.id.lstCartelera);
        //lstCartelera.setAdapter(adaptador);


        return inflater.inflate(R.layout.fragment_cartelera, container, false);
    }


}
