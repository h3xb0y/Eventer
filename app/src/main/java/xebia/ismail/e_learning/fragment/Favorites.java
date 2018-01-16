package xebia.ismail.e_learning.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.favorites.ContactModel;
import xebia.ismail.e_learning.favorites.DBHelper;
import xebia.ismail.e_learning.favorites.RecyclerViewAdapter;

/**
 * tab favorites and reading in recyclerview from the database
 */
public class Favorites extends Fragment {

    ArrayList<ContactModel> arrayList = new ArrayList<>();

            RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_favorites, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewFavorite);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ImageView imgView = (ImageView) v.findViewById(R.id.imageViewEmpty);

        DBHelper dbHelper = new DBHelper(this.getActivity());

        arrayList.addAll(dbHelper.getData());
        adapter = new RecyclerViewAdapter(arrayList, this.getActivity());
        recyclerView.setAdapter(adapter);
        if (arrayList.size() == 0) imgView .setVisibility(View.VISIBLE);
        else         imgView .setVisibility(View.INVISIBLE);

        return v;
    }






}

