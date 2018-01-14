package xebia.ismail.e_learning;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import xebia.ismail.e_learning.favorites.DBHelper;

public class FullInfoActivity extends AppCompatActivity {

    public static String Name,Date,Descr,Time,Price;
    public static int img;
    DBHelper sqlHelper ;
    private Context mContext;
    public static boolean fav;
    String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_info);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (!fav) fab.setVisibility(View.VISIBLE);
        else fab.setVisibility(View.INVISIBLE);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
//            ab.setTitle(R.string.nav_info);
            ab.setTitle(Name);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    sqlHelper= new DBHelper(view.getContext());
                String image =  String.valueOf(img);
                    sqlHelper.addContact(Name,Descr, image,Time,Date, Price);
                Snackbar.make(view, "Мероприятие сохранено", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


            ImageView mImageView = (ImageView) findViewById(R.id.imageViewFullInfo);

        int picture = SetPicture.SetPicture(img);
        mImageView.setImageResource(picture);

        ((TextView) findViewById(R.id.datetext)).setText(Date);
        ((TextView) findViewById(R.id.timetext)).setText(Time);
        ((TextView) findViewById(R.id.description)).setText(Descr);
        ((TextView) findViewById(R.id.moneytext)).setText(Price);
        View number = findViewById(R.id.phoneView);
        JSONObject obj;
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray jArr = obj.getJSONArray("company");

            //FOR EACH POINT, PLACE A MARKER WITH RESPECTIVE DATA
            phone = jArr.getJSONObject(img).getString("num");
            ((TextView) findViewById(R.id.phonetext)).setText(phone);



        } catch (JSONException e) { e.printStackTrace(); }
        View.OnClickListener viewclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +phone));
                startActivity(intent);
            }
        };
        number.setOnClickListener(viewclick);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("descr.json");
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
