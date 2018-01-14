package xebia.ismail.e_learning.recycler;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.pixplicity.fontview.FontAppCompatTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import xebia.ismail.e_learning.FullInfoActivity;
import xebia.ismail.e_learning.Main2Activity;
import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.favorites.DBHelper;



public class aadapter extends RecyclerView.Adapter<aadapter.MyViewHolder>
{
    private Context mContext;
    private List<Itemlist> itemList;

    DBHelper sqlHelper;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        public TextView price;
        public FontAppCompatTextView name;
        public ImageView iv;
        public MaterialFavoriteButton materialFavoriteButtonNice;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            name = (FontAppCompatTextView)itemView.findViewById(R.id.txtTitle);
            price = (TextView)itemView.findViewById(R.id.txtDescription);

            materialFavoriteButtonNice = (MaterialFavoriteButton) itemView.findViewById(R.id.favorite_nice);

        }
    }

    public aadapter(ArrayList<Itemlist> itemList, Context mContext)
    {
        this.mContext = mContext;
        this.itemList = itemList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

        Itemlist item = itemList.get(position);
        String s=item.getName();
        if(item.getName().length()>17)  s = item.getName().substring(0,17)+"...";
        holder.name.setText(s);
        holder.price.setText(item.getDate());
        final String Name = item.getName();
        final String Date = item.getDate();
        final String Descr = item.getDescr();
        final String Time = item.getTime();
        final String Price = item.getPrice();

        final int img = Main2Activity.Info.num;


        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullInfoActivity.img = img;
                FullInfoActivity.Name = Name;
                FullInfoActivity.Date = Date;
                FullInfoActivity.Time = Time;
                FullInfoActivity.Descr = Descr;
                FullInfoActivity.Price = Price;
                FullInfoActivity.fav = false;
                Intent intent = new Intent(mContext, FullInfoActivity.class);
                mContext.startActivity(intent);

            }
        });

        holder.materialFavoriteButtonNice.setFavorite(false, true);
        holder.materialFavoriteButtonNice.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                        if (favorite) {
                            save(mContext,Name,Descr,img,Time,Date,Price);
                            Toast.makeText(mContext, "Сохранено", Toast.LENGTH_LONG).show();
                        } else {
                            final SQLiteDatabase db= sqlHelper.getWritableDatabase();
                            db.delete(DBHelper.TB_NAME,
                                    "name = ?",
                                    new String[] {Name});
                           db.close();
                            Toast.makeText(mContext, "Удалено", Toast.LENGTH_LONG).show();

                        }
                    }
                });
        holder.materialFavoriteButtonNice.setOnFavoriteAnimationEndListener(
                new MaterialFavoriteButton.OnFavoriteAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(MaterialFavoriteButton buttonView, boolean favorite) {
                    }
                });

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
    public void save(Context context,String Name, String Descr, int img,String Time, String Date,String Price){
        sqlHelper= new DBHelper(context);
        String image =  String.valueOf(img);
        sqlHelper.addContact(Name,Descr, image,Time,Date,Price);


    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }
}