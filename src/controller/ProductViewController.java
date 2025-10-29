/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.product;

/**
 * FXML Controller class
 *
 * @author slt
 */
public class ProductViewController implements Initializable {

    @FXML
    private AnchorPane mainPanel;
    @FXML
    private TableView<product> table;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> priceColumn;
    @FXML
    private Label mainText;
    @FXML
    private TextField priceField;
    @FXML
    private TextField nameField;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button delButton;
    
    private ObservableList<product> productList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        productList = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));        
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
       
    }    

    @FXML
    private void addProduct(MouseEvent event) {
        
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        
        productList.add(new product(name, price));
        System.out.println(productList);
        table.setItems(productList);
        nameField.setText("");
        priceField.setText("");

    }

    @FXML
    private void editProduct(MouseEvent event) {
        
        product x = table.getSelectionModel().getSelectedItem();
        String nameValue = nameField.getText();
        double priceValue = Double.parseDouble(priceField.getText());
        
        x.setName(nameValue);
        x.setPrice(priceValue);
        table.refresh();
        
    }

    @FXML
    private void delProduct(MouseEvent event) {
        
        product x = table.getSelectionModel().getSelectedItem();
        productList.remove(x);
        table.refresh();
        nameField.setText("");
        priceField.setText("");
        
    }
    
    @FXML
    private void selectProduct(MouseEvent event) {
        product x = table.getSelectionModel().getSelectedItem();
        nameField.setText(x.getName());
        priceField.setText(String.valueOf(x.getPrice()));
    }
    
}
