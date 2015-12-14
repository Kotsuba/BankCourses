package com.kotsuba.bank.application;

import com.kotsuba.bank.banks.*;
import com.kotsuba.bank.core.Currency;
import com.kotsuba.bank.core.data.DAO;
import com.kotsuba.bank.core.xml.BankInfoXML;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Kotsuba on 03.11.2015.
 */
public class Controller implements Initializable {
    @FXML
    DatePicker date;
    @FXML
    Label statusBar;
    @FXML
    ListView<String> listCourses;
    @FXML
    public void onClickRussia(){
        try {
            if (date.getValue() != null) {
                BankInfoXML bank = new BankInfoCBR(date.getValue());
                DAO db = new DAO();
                if (!db.isCourseDate(bank)) {
                    db.updateCources(bank);
                }
                ObservableList<String> olist = FXCollections.observableArrayList();
                List<Currency> list = db.getCourses(bank);
                for (Currency curr : list) {
                    olist.add(curr.getNominal() + " - " + curr.getName() + " " + curr.getValue());
                }
                listCourses.setItems(olist);
                statusBar.setText("Центральный банк Российской Федерации");
            } else {
                statusBar.setText("Ошибка!Не выбрана дата для поиска актуального курса валют");
            }
        }catch (Exception ex){
            statusBar.setText(ex.toString());
        }
    }
    @FXML
    public void onClickBelarus()throws Exception{
        try {
            if (date.getValue() != null) {
                BankInfoXML bank = new BankInfoNBRB(date.getValue());
                DAO db = new DAO();
                if (!db.isCourseDate(bank)) {
                    db.updateCources(bank);
                }
                ObservableList<String> olist = FXCollections.observableArrayList();
                List<Currency> list = db.getCourses(bank);
                for (Currency curr : list) {
                    olist.add(curr.getNominal() + " - " + curr.getName() + " " + curr.getValue());
                }
                listCourses.setItems(olist);
                statusBar.setText("Национальный банк Республики Беларусь");
            } else {
                statusBar.setText("Ошибка!Не выбрана дата для поиска актуального курса валют");
            }
        } catch (Exception ex){
            statusBar.setText(ex.toString());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setValue(LocalDate.now());
    }
}
