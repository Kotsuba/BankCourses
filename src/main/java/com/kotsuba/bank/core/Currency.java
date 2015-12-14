package com.kotsuba.bank.core;

/**
 * Created by Kotsuba on 03.11.2015.
 */
public class Currency implements Cloneable{
    private int numCode;
    private String charCode;
    private int nominal;
    private String name;
    private double value;

    public Currency(){}

    Currency(Currency currency){
        this.numCode = currency.numCode;
        this.name = currency.name;
        this.nominal=currency.nominal;
        this.value = currency.value;
        this.charCode = currency.charCode;
    }
    public void setNumCode(int numCode){
        this.numCode=numCode;
    }

    public void setCharCode(String charCode){
        this.charCode=charCode;
    }

    public void setName(String name){
        this.name= name;
    }

    public void setValue(double value){
        this.value=value;
    }

    public void setNominal(int nominal){
        this.nominal=nominal;
    }

    public int getNumCode(){
        return numCode;
    }

    public String getCharCode(){
        return charCode;
    }

    public String getName(){
        return name;
    }

    public double getValue(){
        return value;
    }

    public int getNominal(){
        return nominal;
    }

    public String toString(){
        return "NumCode:"+numCode+"\n"+"CharCode:"+charCode+"\n"+"Nominal:"+nominal+"\n"+"Name:"+name+"\n"+"Value:"+value+"\n";
    }
    @Override
    public Currency clone() {
        return  new Currency(this);
    }

}
