package xebia.ismail.e_learning.IntroVideo;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.fragment.Points;
import xebia.ismail.e_learning.fragment.TabMap;

public class VideoActivity extends AppCompatActivity {
    private TextView tvSignUp;
    private Button btnSignIn;
    String url = "https://raw.githubusercontent.com/h3xb0y/Eventer/master/json/points.json";

    public static String name,description;

    private ArrayList<Points> itemlistPoints;
       Points points;
    private ProgressBar bar;
    private TextView textSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        bar = (ProgressBar) this.findViewById(R.id.progressBar);
        textSkip = (TextView) this.findViewById(R.id.tvSignUp) ;
        new myServerCall().execute();
        getWindow().setFormat(PixelFormat.UNKNOWN);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse("http://eventer.comxa.com/");
                Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openlink);
                finish();
            }
        });
        changeStatusBarColor();

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    class myServerCall extends AsyncTask<String, Void, String> {
//        ProgressDialog bar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);

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
//            bar.dismiss();
            bar.setVisibility(View.GONE);
            textSkip.setVisibility(View.VISIBLE);
            itemlistPoints = new ArrayList<>();


            try {
                //loading markers info from json
                JSONObject objectp = new JSONObject(s);
                JSONArray jArrPoints = objectp.getJSONArray("points");

                for (int i =0 ;i<jArrPoints.length();i++) {
                    points= new Points();
                    points.setName(jArrPoints.getJSONObject(i).getString("name"));
                    points.setPosx(jArrPoints.getJSONObject(i).getString("posx"));
                    points.setPosy(jArrPoints.getJSONObject(i).getString("posy"));
                    points.setDescription( jArrPoints.getJSONObject(i).getString("description"));
                    points.setType( jArrPoints.getJSONObject(i).getString("type"));
                    itemlistPoints.add(points);
                }


                TabMap.itemlist = itemlistPoints;

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


}
