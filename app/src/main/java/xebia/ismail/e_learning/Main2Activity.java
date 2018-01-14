package xebia.ismail.e_learning;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import xebia.ismail.e_learning.fragment.TabEvents;
import xebia.ismail.e_learning.fragment.TabInfo;
import xebia.ismail.e_learning.recycler.aadapter;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
            ab.setTitle(R.string.nav_info);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.containerinfo, new Info()).commit();
        }
    }
    public static class Info extends Fragment {
        public static String name = "";
        public static String info ="";
        public static String id = "";
        public static int num;
        private ImageView mImageView;
        public aadapter adapter;
        private static ViewPager mPager;
        private TabLayout mTabLayout;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.content_main2, container, false);
            String packageName = getActivity().getPackageName();
            String translator = getResources().getString(R.string.translator);
            SharedPreferences prefs = getContext().getSharedPreferences(packageName + "_preferences", MODE_PRIVATE);
            mPager = (ViewPager) v.findViewById(R.id.pager);
            mTabLayout = (TabLayout) v.findViewById(R.id.tab_layout);

            mPager.setAdapter(new TabsAdapter(getChildFragmentManager()));
            mTabLayout.setupWithViewPager(mPager);
            mImageView = (ImageView) v.findViewById(R.id.imagee);
            setHasOptionsMenu(true);
            int picture = SetPicture.SetPicture(num);
            mImageView.setImageResource(picture);
            return v;
        }

        class TabsAdapter extends FragmentPagerAdapter {

            public TabsAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new TabInfo();
                    case 1:
                        return new TabEvents();
                }
                return null;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Информация";
                    case 1:
                        return "События";
                }
                return "";
            }
        }

    }

}
