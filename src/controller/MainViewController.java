/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author slt
 */
public class MainViewController implements Initializable {

    @FXML
    private Button productsButton;
    @FXML
    private Button ordersButton;
    @FXML
    private Button tablesButton;
    @FXML
    private AnchorPane mainPanel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewToProducts(MouseEvent event) {
        Stage nuevaV = (Stage) mainPanel.getScene().getWindow();
        
        try {
            Parent nroot = FXMLLoader.load(getClass().getResource("/view/productView.fxml"));
            Scene scene = new Scene(nroot);
            nuevaV.setTitle("Products");
            nuevaV.setScene(scene);
            nuevaV.show();
            
        } catch (IOException ex) {
            System.getLogger(MainViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @FXML
    private void viewToOrders(MouseEvent event) {
        Stage nuevaV = (Stage) mainPanel.getScene().getWindow();
        
        try {
            Parent nroot = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
            Scene scene = new Scene(nroot);
            nuevaV.setTitle("Orders");
            nuevaV.setScene(scene);
            nuevaV.show();
            
        } catch (IOException ex) {
            System.getLogger(MainViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @FXML
    private void viewToTables(MouseEvent event) {
        Stage nuevaV = (Stage) mainPanel.getScene().getWindow();
        
        try {
            Parent nroot = FXMLLoader.load(getClass().getResource("/view/tableView.fxml"));
            Scene scene = new Scene(nroot);
            nuevaV.setTitle("Tables");
            nuevaV.setScene(scene);
            nuevaV.show();
            
        } catch (IOException ex) {
            System.getLogger(MainViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
