package xebia.ismail.e_learning.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.recycler.Itemlist;
import xebia.ismail.e_learning.recycler.RecyclerItem;
import xebia.ismail.e_learning.recycler.aadapter;


public class TabEvents extends Fragment {

    private static List<RecyclerItem> listItems;
    private static ArrayList<Itemlist> itemlist;
    private RecyclerView recyclerView;
    public aadapter adapter;
    static Itemlist contact;
    String nomer;
    Date date1,date2;
    public static int num ;
    public static String url="https://raw.githubusercontent.com/h3xboy/Eventer/master/json/events_new.json";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_tab_events, container, false);
        ImageView image = (ImageView) v.findViewById(R.id.imageView5);
        image.setVisibility(View.INVISIBLE);
        recyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        listItems = new ArrayList<>();

        new myServerCall().execute();

        itemlist= new ArrayList<Itemlist>();
        recyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        return  v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private  class myServerCall extends AsyncTask<String, Void, String>
    {
        ProgressDialog bar;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            bar = ProgressDialog.show(getActivity(), "Загрузка...", "Инициализация данных");
        }


        @Override
        protected String doInBackground(String... params)
        {
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder().url(url).build();

            try
            {
                Response response=client.newCall(request).execute();
                String data=response.body().string();

                if (!response.isSuccessful())
                {
                    throw new IOException("Unexpected code " + response);
                }

                return data;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            bar.dismiss();
            itemlist=new ArrayList<>();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
                String s1 = dateFormat.format(new Date());

                JSONObject object=new JSONObject(s);
                JSONArray arrays=object.getJSONArray(String.valueOf(num));

                for(int i=0;i<arrays.length();i++)
                {
                    JSONObject object1=arrays.getJSONObject(i);
                    String s2 = object1.getString("Date");
                    try {
                    date1 = dateFormat.parse(s1);
                    date2 = dateFormat.parse(s2);
                    } catch (ParseException e) {
                    e.printStackTrace();
                }
                    if (date2.getTime()>=date1.getTime())
                    {
                    contact=new Itemlist();
                    contact.setName(object1.getString("EventName"));
                    contact.setDate(object1.getString("Date"));
                    contact.setDescr(object1.getString("Description"));
                    contact.setTime(object1.getString("Time"));
                    contact.setPrice(object1.getString("Price"));
                    itemlist.add(contact);
                    }
                }
                ImageView image = (ImageView) getView().findViewById(R.id.imageView5);

                if (itemlist.size() == 0)  image.setVisibility(View.VISIBLE);





                adapter =  new aadapter(itemlist, getActivity());
                recyclerView.setAdapter(adapter);

            } catch (JSONException e)
            {
                e.printStackTrace();
            }

//           adapter.notifyDataSetChanged();
        }

    }


}
