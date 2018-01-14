package xebia.ismail.e_learning.fragment;


public class Points {
    private String name;
    private String posx;
    private String posy;
    private String description;
    private String type;

    public Points(){}
    public Points(String name,  String posx, String posy, String description, String type){
        this.name=name;
        this.posx = posx;
        this.posy = posy;
        this.description = description;
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosx() {
        return posx;
    }

    public void setPosx (String posx) {
        this.posx = posx;
    }

    public String getPosy() {
        return posy;
    }

    public void setPosy (String posy) {
        this.posy = posy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }
}
