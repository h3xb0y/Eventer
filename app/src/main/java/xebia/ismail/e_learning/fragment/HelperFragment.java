package xebia.ismail.e_learning.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.helper.FragmentHelper.GridWithTilesAdapter;
import xebia.ismail.e_learning.helper.FragmentHelper.GridWithTilesData;


public class HelperFragment extends Fragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_maps, container, false);
        setupUI(v);


        return v;
    }
    private void setupUI(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new GridWithTilesAdapter(getActivity(), createFakeData()));
    }

    private List<GridWithTilesData> createFakeData() {
        List<GridWithTilesData> data = new ArrayList<>();
        data.add(new GridWithTilesData(R.drawable.food, "Доставка еды"));
        data.add(new GridWithTilesData(R.drawable.service,"Услуги"));
        data.add(new GridWithTilesData(R.drawable.dosug,"Досуг"));
        data.add(new GridWithTilesData(R.drawable.sport,"Спорт"));
        return data;
    }












}

