package com.example.licenta;

public class MoneyObject {
    private String id;
    private String category;
    String type;
    private String description;
    private  String price;
    private String data;

    public MoneyObject(String id, String category, String type, String description, String price, String data){
        this.type=type;
        this.description=description;
        this.price=price;
        this.data=data;
        this.id=id;
        this.category=category;

    }
    public MoneyObject(){
        this.type=null;
        this.description=null;
        this.price=null;
        this.data=null;
        this.category=null;

    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String c){
        this.category=c;
    }

public String getType(){
        return type;
}
public void setType(String type){
        this.type=type;
}

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getName() {
        return description;
    }
    public void setName(String name)
    {
        this.description = name;
    }
    public String getData()
    {
        return data;
    }
    public void setData(String data)
    {
        this.data = data;
    }
    public String getPrice()
    {
        return price;
    }
    public void setPrice(String price)
    {
        this.price = price;
    }

}
