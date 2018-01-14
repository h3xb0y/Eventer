package xebia.ismail.e_learning.news;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import xebia.ismail.e_learning.FullInfoActivity;
import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.SetPicture;
import xebia.ismail.e_learning.favorites.DBHelper;



public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {


    List<NatureItem> mItems;
    private Context mContext;
    DBHelper db ;



    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvDesNature,tvDate;
        public MaterialFavoriteButton materialFavoriteButtonNice;


        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvNature = (TextView)itemView.findViewById(R.id.tv_nature);
            tvDesNature = (TextView)itemView.findViewById(R.id.tv_des_nature);
             materialFavoriteButtonNice = (MaterialFavoriteButton) itemView.findViewById(R.id.favorite_nice);
            tvDate = (TextView)itemView.findViewById(R.id.tv_date);
        }
    }

    public CardAdapter(ArrayList<NatureItem> itemList, Context mContext) {
        this.mItems = itemList;
        this.mContext = mContext ;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final int i) {
        final NatureItem nature = mItems.get(i);
        String s=nature.getDescr();
        if(nature.getDescr().length()>40)  s = nature.getDescr().substring(0,40);
        viewHolder.tvNature.setText(nature.getName());
        viewHolder.tvDesNature.setText(s+"...");
        viewHolder.tvDate.setText(nature.getDate());
        final String Name = nature.getName();
        final String Date = nature.getDate();
        final String Descr = nature.getDescr();
        final String Time = nature.getTime();
        final String Price = nature.getPrice();
        int picture = SetPicture.SetPicture(nature.getId());
        viewHolder.imgThumbnail.setImageResource(picture);
        final int img = nature.getId();
        viewHolder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullInfoActivity.Name = Name;
                FullInfoActivity.Date = Date;
                FullInfoActivity.Time = Time;
                FullInfoActivity.Descr = Descr;
                FullInfoActivity.Price = Price;

                FullInfoActivity.img = nature.getId();
                FullInfoActivity.fav = false;
                Intent intent = new Intent(mContext, FullInfoActivity.class);
                mContext.startActivity(intent);
            }
            });
        viewHolder.materialFavoriteButtonNice.setFavorite(false, true);
        viewHolder.materialFavoriteButtonNice.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                        if (favorite) {
                            save(mContext,Name,Descr,img,Time,Date,Price);
                            Toast.makeText(mContext, "Сохранено", Toast.LENGTH_LONG).show();
                        } else {
                            final SQLiteDatabase sqlhelper= db.getWritableDatabase();
                            sqlhelper.delete(DBHelper.TB_NAME,
                                    "name = ?",
                                    new String[] {Name});
                            sqlhelper.close();
                            Toast.makeText(mContext, "Удалено", Toast.LENGTH_LONG).show();

                        }
                    }
                });
        viewHolder.materialFavoriteButtonNice.setOnFavoriteAnimationEndListener(
                new MaterialFavoriteButton.OnFavoriteAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(MaterialFavoriteButton buttonView, boolean favorite) {
                    }
                });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }

    private void Event(String Name, String Descr) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(CalendarContract.Events.TITLE, Name);
        intent.putExtra(CalendarContract.Events.DESCRIPTION,  Descr);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Guest House");
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

        mContext.startActivity(intent);
    }
    public void save(Context context, String Name, String Descr, int img,String Time,String Date, String Price){
        db= new DBHelper(context);
        String image =  String.valueOf(img);
        db.addContact(Name,Descr, image,Time,Date, Price);


    }
}


