package br.mack.ps2.api;


import java.util.Date;

public class Economia {
    private int id;
    private Date date;
    private int value;

    public Economia(){
        this.id = -1;
        this.date = null;
        this.value = 0;
    }

    public Economia(int id, Date date, int value) {
        this.id = id;
        this.date = date;
        this.value = value;
    }

    public int getId(){ return id;}

    public void setId(int id){this.id = id;}

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Economia{" +
                "id=" + id +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}