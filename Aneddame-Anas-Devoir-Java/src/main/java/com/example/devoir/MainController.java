package com.example.devoir;

import com.example.devoir.dao.ProductDao;
import com.example.devoir.dao.impl.ProductDaoImpl;
import com.example.devoir.model.Product;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController extends Thread implements Initializable {

    @FXML
    private TableColumn<Product, String> nom;

    @FXML
    private TableColumn<Product, Double> prix;
    @FXML
    private TableView<Product> table;

    ObservableList<Product> productList= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
    }


    public void run(){

        while(true){

            try {
                JsonParse.parseJson();
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
            ProductDao dao= new ProductDaoImpl();
            productList.addAll(dao.findAll());
            nom.setCellValueFactory(new PropertyValueFactory<Product,String>("nom"));
            prix.setCellValueFactory(new PropertyValueFactory<Product,Double>("prix"));
            table.setItems(productList);

            try {

                sleep(60000);
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            table.getItems().clear();



        }
    }
}
