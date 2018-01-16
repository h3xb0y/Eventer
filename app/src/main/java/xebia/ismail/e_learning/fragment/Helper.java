package xebia.ismail.e_learning.fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.helper.Album;
import xebia.ismail.e_learning.helper.AlbumsAdapter;

/**
 * a tab with a garbage like calling a taxi or ordering a meal
 */
public class Helper extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    static public String title;
    public static int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);
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
            ab.setTitle(title);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();


    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.h1,
                R.drawable.h2,
                R.drawable.h3,
                R.drawable.h4,
                R.drawable.h5,
                R.drawable.h6,
                R.drawable.h7,
                R.drawable.h8,
                R.drawable.h9,
                R.drawable.h10,
                R.drawable.h11,
                R.drawable.h12,
                R.drawable.h13};
        JSONObject obj;
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray jArr = obj.getJSONArray("helper");
            switch(id){
                case 1:
                    Album a = new Album( jArr.getJSONObject(0).getString("title"), jArr.getJSONObject(0).getString("time"), covers[0], jArr.getJSONObject(0).getString("num"));
                    albumList.add(a);
                    Album b = new Album( jArr.getJSONObject(1).getString("title"), jArr.getJSONObject(1).getString("time"), covers[1], jArr.getJSONObject(1).getString("num"));
                    albumList.add(b);
                    Album c = new Album( jArr.getJSONObject(2).getString("title"), jArr.getJSONObject(2).getString("time"), covers[2], jArr.getJSONObject(2).getString("num"));
                    albumList.add(c);
                    Album d = new Album( jArr.getJSONObject(3).getString("title"), jArr.getJSONObject(3).getString("time"), covers[3], jArr.getJSONObject(3).getString("num"));
                    albumList.add(d);
                    break;
                case 2:
                    Album a1 = new Album( jArr.getJSONObject(4).getString("title"), jArr.getJSONObject(4).getString("time"), covers[4], jArr.getJSONObject(4).getString("num"));
                    albumList.add(a1);
                    Album b1 = new Album( jArr.getJSONObject(5).getString("title"), jArr.getJSONObject(5).getString("time"), covers[5], jArr.getJSONObject(5).getString("num"));
                    albumList.add(b1);
                    Album c1 = new Album( jArr.getJSONObject(6).getString("title"), jArr.getJSONObject(6).getString("time"), covers[6], jArr.getJSONObject(6).getString("num"));
                    albumList.add(c1);
                    Album d1 = new Album( jArr.getJSONObject(7).getString("title"), jArr.getJSONObject(7).getString("time"), covers[7], jArr.getJSONObject(7).getString("num"));
                    albumList.add(d1);
                    Album e1 = new Album( jArr.getJSONObject(8).getString("title"), jArr.getJSONObject(8).getString("time"), covers[8], jArr.getJSONObject(8).getString("num"));
                    albumList.add(e1);
                    break;
                case 3:
                    Album a11 = new Album( jArr.getJSONObject(9).getString("title"), jArr.getJSONObject(9).getString("time"), covers[9], jArr.getJSONObject(9).getString("num"));
                    albumList.add(a11);
                    Album b11 = new Album( jArr.getJSONObject(10).getString("title"), jArr.getJSONObject(10).getString("time"), covers[10], jArr.getJSONObject(10).getString("num"));
                    albumList.add(b11);
                    break;
                case 4:
                    Album a44 = new Album( jArr.getJSONObject(11).getString("title"), jArr.getJSONObject(11).getString("time"), covers[11], jArr.getJSONObject(11).getString("num"));
                    albumList.add(a44);
                    Album b44 = new Album( jArr.getJSONObject(12).getString("title"), jArr.getJSONObject(12).getString("time"), covers[12], jArr.getJSONObject(12).getString("num"));
                    albumList.add(b44);
                    break;

            }



        } catch (JSONException e) { e.printStackTrace(); }
        adapter.notifyDataSetChanged();

    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
