package xebia.ismail.e_learning.favorites;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CalendarContract;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import xebia.ismail.e_learning.FullInfoActivity;
import xebia.ismail.e_learning.R;
import xebia.ismail.e_learning.SetPicture;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<ContactModel> arrayList = new ArrayList<ContactModel>();
    private Context mContext;
    DBHelper sqlHelper;
    SQLiteDatabase db;

    public RecyclerViewAdapter(ArrayList<ContactModel> arrayList,Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        sqlHelper = new DBHelper(mContext);
        final SQLiteDatabase db= sqlHelper.getWritableDatabase();
        final ContactModel model = arrayList.get(position);
        String s=model.getEmail();
        if(model.getEmail().length()>25)  s = model.getEmail().substring(0,25)+"...";
        holder.txtName.setText(model.getName());
        holder.txtLname.setText(s);
        holder.txtImage.setImageResource(R.mipmap.classroom);
        holder.txtDate.setText(model.getDate());
        final int num = Integer.valueOf(model.getImage());
        final String key_name = model.getName();
        final String id = String.valueOf(model.getId());
        final String Name = model.getName();
        final String Descr = model.getEmail();
        final String timee = model.getTimee();
        final String datee = model.getDate();
        final String price = model.getPrice();

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullInfoActivity.Name = Name;
                FullInfoActivity.Date = datee;
                FullInfoActivity.Time = timee;
                FullInfoActivity.Descr = Descr;
                FullInfoActivity.img = num;
                FullInfoActivity.fav = true;
                FullInfoActivity.Price = price;
                Intent intent = new Intent(mContext, FullInfoActivity.class);
                mContext.startActivity(intent);
            }
        });

        int picture = SetPicture.SetPicture(num);
        holder.txtImage.setImageResource(picture);


        holder.another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Display option menu

                PopupMenu popupMenu = new PopupMenu(mContext, holder.txtOptionDigit);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.mnu_item_save:
                                Event(Name, Descr, datee, num);
                                Toast.makeText(mContext, "Сохранено", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.mnu_item_delete:
                                arrayList.remove(position);
                                notifyDataSetChanged();
                                db.delete(DBHelper.TB_NAME,
                                        "name = ?",
                                        new String[] {key_name});
                                db.close();
                                Toast.makeText(mContext, "Событие удалено.", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtLname, Button,txtOptionDigit,txtDate;
        ImageView txtImage;
        LinearLayout another;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtLname = (TextView)itemView.findViewById(R.id.txtEmail);
            txtImage = (ImageView)itemView.findViewById(R.id.txtImage);
            txtOptionDigit = (TextView) itemView.findViewById(R.id.txtOptionDigit);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            another = (LinearLayout) itemView.findViewById(R.id.another);

        }
    }
    private void Event(String Name, String Descr, String date, int num) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");


        String s2 = date.substring(date.length()-2)+
                date.substring(3,5)+date.substring(0,2);
        int year = Integer.valueOf(date.substring(6));


String clname="";
        switch (num) {
            case 0:  clname = "Театр Имени Б.Е. Захавы"; break;
            case 1:         clname = "Театр Имени Б.Е. Захавы"; break;
            case 2:         clname = "к-т Мир";break;
            case 3:          clname = "Театр Имени Б.Е. Захавы";break;
            case 4:         clname = "Олимпия";break;
            case 5:         clname = "Соборная площадь";break;
            case 6:         clname = "Піца Челентано";break;
            case 7:          clname = "Территория Fitness";break;
            case 8:         clname = "ВСК Юність";break;
            case 9:         clname = "Burger Club";break;
            case 10:         clname = "Спорт-бар Бомбардир";break;
            case 11:        clname = "ул. Ганни Світличної, 63";break;
            case 12:        clname = "ДК Гагарина";break;
            case 13:         clname = "ТЦ Гуливер";break;
            case 14:        clname = "Станция Юных Натуралистов";break;
            case 15:         clname = "Империал";break;
            case 16:         clname = "ДК Кирова";break;
            case 17:         clname = "Музей";break;
            case 18:         clname = "Норма";break;
            case 19:        clname = "Парк";break;
            case 20:         clname = "Топлесс";break;
            case 21:         clname = "Каспий";break;
            case 22:         clname = "Дионис";break;
            case 23:        clname = "Смерекова Хата";break;}

        Calendar cal = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date1 = simpleDateFormat.parse(date);
            cal.setTime(date1);

        } catch (ParseException e) {              // Insert this block.
            e.printStackTrace();
        }
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        intent.putExtra(CalendarContract.Events.TITLE, Name);
        intent.putExtra(CalendarContract.Events.DESCRIPTION,  Descr);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, clname);
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

        mContext.startActivity(intent);
    }

}