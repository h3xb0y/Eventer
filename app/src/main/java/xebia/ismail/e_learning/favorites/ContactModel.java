package xebia.ismail.e_learning.favorites;



public class ContactModel {
    private String name;
    private String email;
    private int id;
    private String image;
    private String date,time,price;

    public ContactModel(int id,String name, String email, String image, String time, String date,String price) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setImage(image);
        this.setTimee(time);
        this.setDate(date);
        this.setPrice(price);
    }
    public ContactModel(String name, String email, String image,String time, String date, String price) {
        this.setName(name);
        this.setEmail(email);
        this.setImage(image);
        this.setTimee(time);
        this.setDate(date);
        this.setPrice(price);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {this.image = image;}

    public String getTimee() {
        return time;
    }
    public void setTimee(String time) {this.time = time;}

    public String getDate() {
        return date;
    }
    public void setDate(String date) {this.date = date;}

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {this.price = price;}

}
