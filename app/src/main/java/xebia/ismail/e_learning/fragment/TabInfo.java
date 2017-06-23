package xebia.ismail.e_learning.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pixplicity.fontview.FontAppCompatTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import xebia.ismail.e_learning.Main2Activity;
import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.recycler.Itemlist;
import xebia.ismail.e_learning.recycler.RecyclerItem;
import xebia.ismail.e_learning.recycler.aadapter;

public class TabInfo extends Fragment {



    static String[] rank = new String[] {"1", "2", "3"} ;
    public static String name = "";
    public static String info ="";
    public static String id = "";
    public static String fulldescr ="";
    public static int num;
    private ImageView mImageView;
    private static List<RecyclerItem> listItems;
    private static ArrayList<Itemlist> itemlist;
    private RecyclerView recyclerView;
    public aadapter adapter;
    static Itemlist contact;
    String nomer;
    private boolean istr;
    public static String url="http://eventer.at.ua/events_new.json";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_info, container, false);
        ((FontAppCompatTextView) v.findViewById(R.id.name)).setText(name);
        ((FontAppCompatTextView) v.findViewById(R.id.address)).setText(info);
        FontAppCompatTextView descr = (FontAppCompatTextView) v.findViewById(R.id.description);

        JSONObject obj;
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray jArr = obj.getJSONArray("company");

            //FOR EACH POINT, PLACE A MARKER WITH RESPECTIVE DATA
            String desc = jArr.getJSONObject(num).getString("description");
            String time = jArr.getJSONObject(num).getString("time");
            nomer = jArr.getJSONObject(num).getString("num");
            ((FontAppCompatTextView) v.findViewById(R.id.description)).setText(desc);
            ((FontAppCompatTextView) v.findViewById(R.id.time)).setText(time);

             ((FontAppCompatTextView) v.findViewById(R.id.num)).setText(nomer);



        } catch (JSONException e) { e.printStackTrace(); }




        View number = v.findViewById(R.id.number);
        View.OnClickListener viewclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nomer.equals("Номер неизвестен.")){
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +nomer));
                startActivity(intent);}
            }
        };
        number.setOnClickListener(viewclick);
        return v;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("descr.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

