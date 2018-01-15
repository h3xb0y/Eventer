package xebia.ismail.e_learning.news;


public class NewsItem {
    private String mName;
    private String mDate;
    private String mThumbnail;
    private int id;
    private String mDes,mTime,mPrice;

    public NewsItem() {}


    public NewsItem(String mName, String mDate, String mThumbnail, int id, String mDes, String mTime, String mPrice)
    {
        this.mName = mName;
        this.mDate = mDate;
        this.mThumbnail = mThumbnail;
        this.id = id;
        this.mDes = mDes;
        this.mTime = mTime;
        this.mPrice = mPrice;
    }

    public String getName() {
        return mName;
    }
    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDate() {
        return mDate;
    }
    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getThumbnail() {
        return mThumbnail;
    }
    public void setThumbnail(String mThumbnail) {this.mThumbnail = mThumbnail;}

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}

    public String getDescr()
    {
        return mDes;
    }
    public void setDescr(String mDes)
    {
        this.mDes = mDes;
    }

    public String getTime()
    {
        return mTime;
    }
    public void setTime(String mTime)
    {
        this.mTime = mTime;
    }

    public String getPrice()
    {
        return mPrice;
    }
    public void setPrice(String mPrice)
    {
        this.mPrice = mPrice;
    }


}
