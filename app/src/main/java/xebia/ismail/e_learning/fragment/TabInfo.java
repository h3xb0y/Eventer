package xebia.ismail.e_learning.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.recycler.Itemlist;
import xebia.ismail.e_learning.recycler.RecyclerItem;
import xebia.ismail.e_learning.recycler.aadapter;

public class TabInfo extends Fragment {



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
   public static String url="https://raw.githubusercontent.com/h3xb0y/Eventer/master/json/description.json";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_info, container, false);
        ((FontAppCompatTextView) v.findViewById(R.id.name)).setText(name);
        ((FontAppCompatTextView) v.findViewById(R.id.address)).setText(info);
        FontAppCompatTextView descr = (FontAppCompatTextView) v.findViewById(R.id.description);



        new myServerCall().execute();


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




    class myServerCall extends AsyncTask<String, Void, String> {
        ProgressDialog bar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar = ProgressDialog.show(TabInfo.this.getActivity(), "Загрузка...", "Инициализация данных");
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
                JSONObject object = new JSONObject(s);
                JSONArray jArr = object.getJSONArray("company");
                //FOR EACH POINT, PLACE A MARKER WITH RESPECTIVE DATA
                String desc = jArr.getJSONObject(num).getString("description");
                String time = jArr.getJSONObject(num).getString("time");
                nomer = jArr.getJSONObject(num).getString("num");
                ((FontAppCompatTextView) getView().findViewById(R.id.description)).setText(desc);
                ((FontAppCompatTextView) getView().findViewById(R.id.time)).setText(time);

                ((FontAppCompatTextView) getView().findViewById(R.id.num)).setText(nomer);




            } catch (JSONException e) {
                e.printStackTrace();
            }

//           adapter.notifyDataSetChanged();
        }

    }


}

