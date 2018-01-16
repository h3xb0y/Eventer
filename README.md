![alt text](https://github.com/h3xb0y/Eventer/blob/master/image/main2.png "main.png")

[![](https://github.com/h3xb0y/Eventer/blob/master/image/download-button-android-new.png)](https://github.com/h3xb0y/Eventer/blob/master/app.apk)

![alt text](https://github.com/h3xboy/Eventer/blob/master/image/1497866662704.gif "WelcomeActivity")
![alt text](https://github.com/h3xboy/Eventer/blob/master/image/1497866139404.gif "WelcomeActivity")
![alt text](https://github.com/h3xboy/Eventer/blob/master/image/1497866227144.gif "WelcomeActivity")

## Join development!

**Build status:** [![Build Status](https://travis-ci.org/h3xb0y/Eventer.svg?branch=master)](https://travis-ci.org/h3xb0y/Eventer)

**Start contributing:** Make sure you read [SETUP.md](https://github.com/h3xb0y/Eventer/blob/master/SETUP.md) when you start working on this project. Basically: Fork this repository and contribute back using pull requests to the master branch.
Easy starting points are also reviewing [pull requests](https://github.com/h3xb0y/Eventer/pulls).

**Latest version** [apk](https://github.com/h3xb0y/Eventer/blob/master/app.apk)

[![GitHub followers](https://img.shields.io/github/followers/h3xb0y.svg?style=social&label=Follow)](https://github.com/h3xb0y)
[![GitHub stars](https://img.shields.io/github/stars/h3xb0y/Eventer.svg?style=social&label=Star)]()

[![Twitter URL](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=https://github.com/h3xb0y/Eventer)
[![Twitter Follow](https://img.shields.io/twitter/follow/h3xb0y.svg?style=social)](https://twitter.com/h3xb0y)

## Work algorithm
![Screenshot](https://github.com/h3xboy/Eventer/blob/master/image/how.png "how")
##### Parsing events is as follows:
### .json
```
       "0":[
        {
            "EventName":"ПРОСНИСЬ И ПОЙ!",
            "Date":"24.06.2017",
	           "Time":"18:00",
            "Description":"Постановка ПРОСНИСЬ и ПОЙ",
            "Price":"50грн"
            
        },
        {
            "EventName":"ПИГМАЛИОН",
            "Date":"25.06.2017",
	           "Time":"18:00",
            "Description":"Постановка ПИГМАЛИОН",
            "Price":"50грн"
        }
        ],
```
### Java
```
 try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
                String s1 = dateFormat.format(new Date());

                JSONObject object=new JSONObject(s);
                JSONArray arrays=object.getJSONArray(String.valueOf(num));

                for(int i=0;i<arrays.length();i++)
                {
                    JSONObject object1=arrays.getJSONObject(i);
                    String s2 = object1.getString("Date");
                    try {
                    date1 = dateFormat.parse(s1);
                    date2 = dateFormat.parse(s2);
                    } catch (ParseException e) {
                    e.printStackTrace();
                }
                    if (date2.getTime()>=date1.getTime())
                    {
                    contact=new Itemlist();
                    contact.setName(object1.getString("EventName"));
                    contact.setDate(object1.getString("Date"));
                    contact.setDescr(object1.getString("Description"));
                    contact.setTime(object1.getString("Time"));
                    contact.setPrice(object1.getString("Price"));
                    itemlist.add(contact);
                    }
}
```

### libs
##### build.gradle
```
    compile 'pl.droidsonroids.gif:android-gif-drawable:1.1.+'
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support:design:25.3.1'
    compile 'com.android.support:recyclerview-v7:25.3.1'
    compile 'com.android.support:cardview-v7:25.3.1'
    compile 'de.hdodenhof:circleimageview:1.3.0'
    compile 'com.rengwuxian.materialedittext:library:2.1.4'
    compile 'com.pixplicity.letterpress:library:1.0'
    compile 'com.google.android.gms:play-services:10.2.6'
    compile 'com.parse:parse-android:1.10.3'
    compile 'com.mcxiaoke.popupmenu:library:1.0.3'
    compile 'com.squareup.retrofit2:retrofit:2.2.0'
    compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta4'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.readystatesoftware.sqliteasset:sqliteassethelper:+'
    compile 'com.github.ivbaranov:materialfavoritebutton:0.1.4'
    compile 'com.android.support:support-v4:25.3.1'
    compile 'com.android.support:support-vector-drawable:25.3.1'
    compile 'com.github.bumptech.glide:glide:3.7.0'
```

