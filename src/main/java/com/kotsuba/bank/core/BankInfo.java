package com.kotsuba.bank.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Kotsuba on 03.11.2015.
 */
public interface BankInfo {
    List<Currency> getCurrencyList()throws Exception;
    default String getDate(LocalDate date){
        DateTimeFormatter dateF = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        return dateF.format(date);
    }
}
