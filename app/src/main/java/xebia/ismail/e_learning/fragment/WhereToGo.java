package xebia.ismail.e_learning.fragment;


import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import xebia.ismail.e_learning.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xebia.ismail.e_learning.favorites.DBHelper;
import xebia.ismail.e_learning.news.CardAdapter;
import xebia.ismail.e_learning.news.NatureItem;
import xebia.ismail.e_learning.recycler.RecyclerItem;

/**
 * Created by Admin on 5/25/2016.
 */
public class WhereToGo extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NatureItem> itemlist;
    public String url = "https://raw.githubusercontent.com/h3xboy/Eventer/master/json/events_new.json";
    NatureItem contact;
    private CardAdapter adapter;
    private Date date1,date2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.where_to_go, container, false);
        new myServerCall().execute();

        //itemlist= new ArrayList<Itemlist>();
        recyclerView = (RecyclerView) v.findViewById(R.id.RecyclerWTG);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    class myServerCall extends AsyncTask<String, Void, String> {
        ProgressDialog bar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar = ProgressDialog.show(WhereToGo.this.getActivity(), "Загрузка...", "Инициализация данных");
        }


        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            try {
                Response response = client.newCall(request).execute();
                String data = response.body().string();

                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            bar.dismiss();
            itemlist = new ArrayList<>();

            try {
                int timer = 0;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
                String s1 = dateFormat.format(new Date());

                JSONObject object = new JSONObject(s);
                for (int i = 0; i < object.length(); i++) {
                    if (object.getJSONArray(String.valueOf(i)).length()!= 0){
                    JSONArray arrays = object.getJSONArray(String.valueOf(i));
                    JSONObject object1 = arrays.getJSONObject(0);
                        String s2 = object1.getString("Date");
                        try {
                            date1 = dateFormat.parse(s1);
                            date2 = dateFormat.parse(s2);
                        } catch (ParseException e) {              // Insert this block.
                            e.printStackTrace();
                        }
                        if ((date2.getTime() >=  date1.getTime())){
                    contact = new NatureItem();
                    contact.setName(object1.getString("EventName"));
                    contact.setDate(object1.getString("Date"));
                    contact.setThumbnail("http://eventer.s-host.net/app/"+i+".jpg");
                    contact.setId(i);
                    contact.setTime(object1.getString("Time"));
                    contact.setDescr(object1.getString("Description"));
                            contact.setPrice(object1.getString("Price"));
                            itemlist.add(contact);

                        timer++;}}
                }

                Collections.sort(itemlist, new Comparator<NatureItem>() {
                    public int compare(NatureItem o1, NatureItem o2) {
                        if (o1.getDate() == null || o2.getDate() == null)
                            return 0;
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });


                adapter = new CardAdapter(itemlist, getActivity());
                recyclerView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

//           adapter.notifyDataSetChanged();
        }

    }



}
