package com.kotsuba.bank.core.data;

import com.kotsuba.bank.core.Currency;
import com.kotsuba.bank.core.xml.BankInfoXML;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotsuba on 03.11.2015.
 */
public class DAO {
    private Connection connection=null;
    private String urlDb="jdbc:mysql://localhost:3306/Banks";
    private String loginDb="root";
    private String passwordDb="root";
    Currency curr=null;

    private Connection getConnection()throws Exception{
        Driver myDriver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver( myDriver );
        connection= DriverManager.getConnection(urlDb, loginDb, passwordDb);
        return connection;
    }
    private void insertCurrency(BankInfoXML bank)throws Exception{
        Statement statement=getConnection().createStatement();
        List<Currency> list=bank.getCurrencyList();
        int bankId=bank.getBankId();
        for (Currency currency: list){
            int numCode=currency.getNumCode();
            String charCode=currency.getCharCode();
            int nominal=currency.getNominal();
            String name=currency.getName();
            String insert="INSERT INTO banks.currency (currency.Bank_id,currency.numCode,currency.charCode,currency.nominal,currency.name)VALUES ('"+bankId+"','"+numCode+"','"+charCode+"','"+nominal+"','"+name+"')";
            statement.executeUpdate(insert);
        }
    }
    public void updateCources(BankInfoXML bank)throws Exception{
        Statement statement=getConnection().createStatement();
        int i=0;
        List<Currency> list=bank.getCurrencyList();
        List<Integer> currencyIdList=getCurrencyId(bank);
        for (Currency currency: list){
            String update="INSERT INTO banks.course (course.Currency_id,course.date,course.value)VALUES ('"+currencyIdList.get(i)+"','"+bank.getDate(BankInfoXML.date)+"','"+currency.getValue()+"')";
            i++;
            statement.executeUpdate(update);
        }
        statement.executeUpdate("INSERT  INTO banks.coursedate (coursedate.Bank_id,coursedate.date) VALUES('"+bank.getBankId()+"','"+bank.getDate(BankInfoXML.date)+"')");
    }
    private List<Integer> getCurrencyId(BankInfoXML bank)throws Exception{
        List<Integer> currencyId=new ArrayList<Integer>();
        Statement statement=getConnection().createStatement();
        ResultSet rs=statement.executeQuery("Select id FROM banks.currency WHERE Bank_id='"+bank.getBankId()+"'");
        while(rs.next()){
            currencyId.add(rs.getInt("id"));
        }
        return currencyId;
    }
    public boolean isCourseDate(BankInfoXML bank)throws Exception{
        boolean result=false;
        int i=0,k=0;
        Statement statement=getConnection().createStatement();
        ResultSet courseCountBank=statement.executeQuery("Select Bank_id FROM banks.coursedate WHERE date='"+bank.getDate(BankInfoXML.date)+"'");
        while (courseCountBank.next()) {
            i++;
        }
        Statement statement1=getConnection().createStatement();
        ResultSet countBank=statement1.executeQuery("Select Count(*) FROM banks.bank");
        while(countBank.next()){
            k=countBank.getInt(1);
        }
        countBank.close();
        if(i==k){
            result=true;
        }
        else {
            if(i==0){
                result=false;
            }
            else {
                courseCountBank.beforeFirst();
                while (courseCountBank.next()) {
                    if (bank.getBankId() == courseCountBank.getInt("Bank_id")) {
                        result=true;
                        break;
                    }
                    else{
                        result=false;
                    }
                }
                countBank.close();
            }
        }
        return result;
    }
    public List<Currency> getCourses(BankInfoXML bank)throws Exception{
        curr=new Currency();
        Statement statement = getConnection().createStatement();
        ResultSet rs=statement.executeQuery("SELECT numcode,charcode,nominal,name,value FROM actualcourse WHERE Bank_id='"+bank.getBankId()+"' AND date='"+bank.getDate(BankInfoXML.date)+"'");
        List<Currency> list=new ArrayList<Currency>();
        while (rs.next()) {
            curr.setNumCode(rs.getInt("numcode"));
            curr.setCharCode(rs.getString("charcode"));
            curr.setNominal(rs.getInt("nominal"));
            curr.setName(rs.getString("name"));
            curr.setValue(rs.getDouble("value"));
            list.add(curr.clone());
        }
        return list;
    }
}
