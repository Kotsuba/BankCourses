package com.kotsuba.bank.core.xml;

import com.kotsuba.bank.core.Currency;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Kotsuba on 03.11.2015.
 */
public class ParserXML extends DefaultHandler {

    private String numCodeTag;
    private String charCodeTag;
    private String nameTag;
    private String valueTag;
    private String nominalCodeTag;

    public ParserXML(Map<String, String> tags) {
        this.numCodeTag = tags.get("NumCode");
        this.charCodeTag = tags.get("CharCode");
        this.nameTag = tags.get("Name");
        this.valueTag = tags.get("Value");
        this.nominalCodeTag=tags.get("Nominal");
    }

    ArrayList<Currency> list=null;
    Currency curr=null;
    String thisElement;
    ArrayList<Currency> getData(){
        return list;
    }
    @Override
    public void startDocument() throws SAXException {
        curr=new Currency();
        list = new ArrayList<Currency>();
    }
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement=qName;
    }
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if(thisElement.equals(valueTag)){
            list.add(curr.clone());
        }
        thisElement="";
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals(numCodeTag)) {
            curr.setNumCode(new Integer(new String(ch, start, length)));
        }
        if (thisElement.equals(charCodeTag)) {
            curr.setCharCode(new String(ch, start, length));
        }
        if (thisElement.equals(nominalCodeTag)) {
            curr.setNominal(new Integer(new String(ch, start, length)));
        }
        if (thisElement.equals(nameTag)) {
            curr.setName(new String(ch, start, length));
        }
        if (thisElement.equals(valueTag)) {
            String value=new String(ch, start, length);
            curr.setValue(new Double(value.replace(",",".")));
        }
    }
    @Override
    public void endDocument(){
    }
}
