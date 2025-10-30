/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.command;
import model.product;

/**
 * FXML Controller class
 *
 * @author slt
 */
public class CommandViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    command newCommand;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        int counter = 0;
        newCommand = new command(counter);

    }    
    
   
    @FXML
    public void addCommand(){
        
        //product x = table.getSelectionModel().getSelectedItem();
        //newCommand.addProductToCommand(x);
        
    }
    
    @FXML
    public void delCommand(product x){
        newCommand.delProductFromCommand(x);
    }
    
    @FXML
    public void editCommand(product x){
        newCommand.addProductToCommand(x);
    }
    
}
