/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Order;
import model.Product;

/**
 * FXML Controller class
 *
 * @author slt
 */
public class OrderViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane mainPanel;
    @FXML
    private TableView<Order> table;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> orderColumn;
    @FXML
    private TextField productField;
    
    private ObservableList<Order> orderList;
    
    public int counter;
    
    ProductViewController pController = new ProductViewController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        orderList = txtToOrder();
        counter = orderList.size();
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));        
        orderColumn.setCellValueFactory(new PropertyValueFactory("order"));
        table.setItems(orderList);
       
    }       
    
    @FXML
    private void addOrder(MouseEvent event) {
        
        String[] productListString = productField.getText().split("/");
        ObservableList<Product> productList = pController.txtToProduct();
        ArrayList<Product> productListForOrder = new ArrayList();
        
        for(String pS : productListString){
            for(Product p : productList){
                if(pS.equals(p.getName())){
                    productListForOrder.add(p);
                }
            }
        }
        
        counter = orderList.size() + 1;
        Order x = new Order(counter,productListForOrder);
        orderList.add(x);
        dbManager(orderList,x,1);
        table.refresh();
        
    }

    @FXML
    private void deleteOrder(MouseEvent event) {
        Order selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        // Quitar producto de la lista
        orderList.remove(selected);

        // Si la lista queda vacía, limpiar el archivo completamente
        if (orderList.isEmpty()) {
            dbManager(orderList, null, 3);  // vacía la base de datos
        } else {
            dbManager(orderList, null, 2);  // reescribe con los productos que quedan
        }
        
        counter --;
        table.refresh();
        productField.setText("");
    }

    @FXML
    private void editOrder(MouseEvent event) {
        Order x = table.getSelectionModel().getSelectedItem();
        
        String[] productListString = productField.getText().split("/");
        ObservableList<Product> productList = pController.txtToProduct();
        ArrayList<Product> productListForOrder = new ArrayList();
        
        for(String pS : productListString){
            for(Product p : productList){
                if(pS.equals(p.getName())){
                    x.addProductToOrder(p);
                }
            }
        }
       
        table.refresh();
        
        
        dbManager(orderList, null, 2);
    }
    
    private ObservableList<Order> txtToOrder(){
        
        ObservableList<Order> orderListArray = FXCollections.observableArrayList();
        
        try(BufferedReader bw = new BufferedReader(new FileReader("src/txtDatabase/orders/database.txt"))){
            
            String line;
            ArrayList<Product> productList = new ArrayList();
            
            while((line = bw.readLine()) != null){
                
                String[] lineArray = line.split(";");
                
                String listaLimpia = lineArray[1].replace("[", "").replace("]", "");
                
                String[] productArray = listaLimpia.split(",");
                int i = 0;
                for(String x : productArray){
                    
                    x = x.trim();
                    String[] productAtt = x.split("/");
                    
                    productList.add(new Product(productAtt[0],Double.parseDouble(productAtt[1]),Integer.parseInt(productAtt[2])));
                    productAtt = null;
                }
                orderListArray.add(new Order(Integer.parseInt(lineArray[0]),productList));
                productList = new ArrayList();
                counter = Integer.parseInt(lineArray[0]) + 1;
                
            }
            
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        
        return orderListArray;
        
    }
    
    private void dbManager(List<Order> orderList, Order x, int mode) {
        String path = "src/txtDatabase/orders/database.txt";

        try {
            if (mode == 1) {
                try (BufferedWriter bf = new BufferedWriter(new FileWriter(path, true))) {
                    if (x != null && x.toString() != null && !x.toString().isEmpty()) {
                        bf.write(x.toString());
                        bf.newLine();
                        System.out.println("Order added");
                    }
                }

            } else if (mode == 2) {
                try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))) {
                    for (Order p : orderList) {
                        if (p != null && p.toString() != null) {
                            bf.write(p.toString());
                            bf.newLine();
                        }
                    }
                    System.out.println("DB Refreshed");
                }

            } else if (mode == 3) {
                try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))) {
                    bf.write("");
                    System.out.println("DB Cleaned");
                }
            }

        } catch (IOException e) {
            System.out.println("Error in dbManager: " + e.getMessage());
        }
    }

    @FXML
    private void selectOrder(MouseEvent event) {
        Order x = table.getSelectionModel().getSelectedItem();
        productField.setText(String.valueOf(x.getOrder()));
    
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
