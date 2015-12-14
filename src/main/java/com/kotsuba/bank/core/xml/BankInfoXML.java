package com.kotsuba.bank.core.xml;

import com.kotsuba.bank.core.BankInfo;
import com.kotsuba.bank.core.Currency;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Kotsuba on 03.11.2015.
 */
public abstract class BankInfoXML implements BankInfo {
    List<Currency> list=null;
    public static LocalDate date=null;
    @Override
    public List<Currency> getCurrencyList()throws Exception{
        BufferedInputStream buffer=null;
        HttpURLConnection connection=null;
        URL ur = new URL(getUrl()+getDateUrlParametr()+"="+getDateFormat(date));
        connection = (HttpURLConnection) ur.openConnection();
        buffer=new BufferedInputStream(connection.getInputStream());
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        ParserXML xml = new ParserXML(getTags());
        parser.parse(buffer,xml);
        list=xml.getData();
        return list;
    }
    abstract public int getBankId();

    abstract public String getUrl();

    abstract public Map<String,String> getTags();

    abstract public String getDateUrlParametr();

    abstract public String getDateFormat(LocalDate date);

}
