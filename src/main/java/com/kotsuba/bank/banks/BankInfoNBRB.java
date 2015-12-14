package com.kotsuba.bank.banks;

import com.kotsuba.bank.core.xml.BankInfoXML;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kotsuba on 03.11.2015.
 */
public class BankInfoNBRB extends BankInfoXML {

    public BankInfoNBRB(LocalDate date){
        BankInfoXML.date=date;
    }
    @Override
    public String getUrl() {
        return "http://www.nbrb.by/Services/XmlExRates.aspx?";
    }

    @Override
    public Map<String, String> getTags() {
        Map<String,String> tags = new HashMap<>();
        tags.put("NumCode","NumCode");
        tags.put("CharCode","CharCode");
        tags.put("Nominal","Scale");
        tags.put("Name","Name");
        tags.put("Value","Rate");
        return tags;
    }

    @Override
    public String getDateFormat(LocalDate date) {
        DateTimeFormatter dateF = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        return dateF.format(date);
    }

    @Override
    public String getDateUrlParametr() {
        return "ondate";
    }

    @Override
    public int getBankId(){
        return 2;
    }
}
