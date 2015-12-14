package com.kotsuba.bank.banks;

import com.kotsuba.bank.core.xml.BankInfoXML;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kotsuba on 03.11.2015.
 */
public class BankInfoCBR extends BankInfoXML {
    public BankInfoCBR(LocalDate date){
        BankInfoXML.date=date;
    }
    @Override
    public String getUrl() {
        return "http://www.cbr.ru/scripts/XML_daily.asp?";
    }

    @Override
    public Map<String, String> getTags() {
        Map<String,String> tags = new HashMap<>();
        tags.put("NumCode","NumCode");
        tags.put("CharCode","CharCode");
        tags.put("Nominal","Nominal");
        tags.put("Name","Name");
        tags.put("Value","Value");
        return tags;
    }

    @Override
    public String getDateFormat(LocalDate date) {
        //dd/MM/YYYY
        DateTimeFormatter dateF = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return dateF.format(date);
    }

    @Override
    public String getDateUrlParametr() {
        return "date_req";
    }

    @Override
    public int getBankId(){
        return 1;
    }

}
