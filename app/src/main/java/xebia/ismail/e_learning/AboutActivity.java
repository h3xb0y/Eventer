package xebia.ismail.e_learning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(R.string.nav_about);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new AboutFragment()).commit();
        }
    }

    public static class AboutFragment extends Fragment {
        int i=0;
        String link2 = "https://github.com/h3xboy/Eventer";
        String link1 = "https://vk.com/h3xb0y";

        private Context mContext;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_about, container, false);


            //View changelogView = v.findViewById(R.id.changelogView);
            View developersView = v.findViewById(R.id.developersView);
            View licensesView = v.findViewById(R.id.licensesView);
            View vkontakte = v.findViewById(R.id.vkontakte);
            View site = v.findViewById(R.id.siteview);


            String packageName = getActivity().getPackageName();
            String translator = getResources().getString(R.string.translator);


            View.OnClickListener viewclick2 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri address = Uri.parse(link1);
                    Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                    startActivity(openlink);

                }
            };
            developersView.setOnClickListener(viewclick2);
            View.OnClickListener viewclick3 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri address = Uri.parse(link2);
                    Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                    startActivity(openlink);

                }
            };
            licensesView.setOnClickListener(viewclick3);
            View.OnClickListener siteclick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri address = Uri.parse("http://eventer.comxa.com/");
                    Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                    startActivity(openlink);

                }
            };
            site.setOnClickListener(siteclick);

            SharedPreferences prefs = getContext().getSharedPreferences(packageName + "_preferences", MODE_PRIVATE);
            return v;
        }

    }
}

