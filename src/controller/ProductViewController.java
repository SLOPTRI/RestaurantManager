/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Product;

/**
 * FXML Controller class
 *
 * @author slt
 */
public class ProductViewController implements Initializable {

    @FXML
    private AnchorPane mainPanel;
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> priceColumn;
    @FXML
    private TableColumn<?, ?> quantityColumn;
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
    @FXML
    private TextField quantityField;
    
    private ObservableList<Product> productList;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        productList = txtToProduct();
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));        
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory("quantity"));
        table.setItems(productList);
       
    }    

    @FXML
    private void addProduct(MouseEvent event) {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Product newProduct = new Product(name, price,quantity);
        productList.add(newProduct);

        dbManager(productList, newProduct, 1); // agrega solo el nuevo producto

        table.refresh();
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }


    @FXML
    private void editProduct(MouseEvent event) {
        
        Product x = table.getSelectionModel().getSelectedItem();
        String nameValue = nameField.getText();
        double priceValue = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        
        x.setName(nameValue);
        x.setPrice(priceValue);
        x.setQuantity(quantity);
        table.refresh();
        
        dbManager(productList, null, 2);
        
    }

    @FXML
    private void delProduct(MouseEvent event) {
        Product selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        // Quitar producto de la lista
        productList.remove(selected);

        // Si la lista queda vacía, limpiar el archivo completamente
        if (productList.isEmpty()) {
            dbManager(productList, null, 3);  // vacía la base de datos
        } else {
            dbManager(productList, null, 2);  // reescribe con los productos que quedan
        }

        table.refresh();
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    
    @FXML
    private void selectProduct(MouseEvent event) {
        Product x = table.getSelectionModel().getSelectedItem();
        nameField.setText(x.getName());
        priceField.setText(String.valueOf(x.getPrice()));
        quantityField.setText(String.valueOf(x.getQuantity()));
    }
    
    
    private void dbManager(List<Product> productList, Product x, int mode) {
        String path = "src/txtDatabase/products/database.txt";

        try {
            if (mode == 1) {
                // Mode 1: agregar un producto nuevo al final del archivo
                try (BufferedWriter bf = new BufferedWriter(new FileWriter(path, true))) {
                    if (x != null && x.toString() != null && !x.toString().isEmpty()) {
                        bf.write(x.toString());
                        bf.newLine();
                        System.out.println("Order added");
                    }
                }

            } else if (mode == 2) {
                // Mode 2: sobrescribir el archivo completo con la lista actual
                try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))) {
                    for (Product p : productList) {
                        if (p != null && p.toString() != null) {
                            bf.write(p.toString());
                            bf.newLine();
                        }
                    }
                    System.out.println("DB Refreshed");
                }

            } else if (mode == 3) {
                // Mode 3: vaciar la base de datos
                try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))) {
                    bf.write("");
                    System.out.println("DB Cleaned");
                }
            }

        } catch (IOException e) {
            System.out.println("Error in dbManager: " + e.getMessage());
        }
    }
    
    ObservableList<Product> txtToProduct(){
        
        ObservableList<Product> productListArray = FXCollections.observableArrayList();
        
        try(BufferedReader bw = new BufferedReader(new FileReader("src/txtDatabase/products/database.txt"))){
            
            String line;
            
            while((line = bw.readLine()) != null){
                
                String[] lineArray = line.split("/");
                
                productListArray.add(new Product(lineArray[0],Double.parseDouble(lineArray[1]),Integer.parseInt(lineArray[2])));
                
            }
            
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        
        return productListArray;
        
    }

    @FXML
    private void viewToMain(MouseEvent event) {
        
        Stage nuevaV = (Stage) mainPanel.getScene().getWindow();
        
        try {
            Parent nroot = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
            Scene scene = new Scene(nroot);
            nuevaV.setTitle("Tables");
            nuevaV.setScene(scene);
            nuevaV.show();
            
        } catch (IOException ex) {
            System.getLogger(MainViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
}
