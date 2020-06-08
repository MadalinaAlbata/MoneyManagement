package com.example.licenta;

class Expense {

    private String nume;
    private int img;

    public Expense(String n,int img){
        this.nume=n;
        this.img=img;
    }

    public void setNume(String n){

        this.nume=n;
    }

    public String getNume(){

        return this.nume;
    }

    public void setImg(int i)
    {
        this.img=i;
    }
    public int getImg(){

        return this.img;
    }
}
