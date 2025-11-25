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
import model.Table;

/**
 * FXML Controller class
 *
 * @author slt
 */
public class TableViewlController implements Initializable {

    @FXML
    private AnchorPane mainPanel;
    @FXML
    private Label tableText;
    @FXML
    private TableView<?> tableTable;
    @FXML
    private TableColumn<?, ?> cTableNumber;
    @FXML
    private TableColumn<?, ?> cOrders;
    @FXML
    private TableColumn<?, ?> cPrice;
    @FXML
    private Button bCashOut;
    @FXML
    private TextField tPrice;
    @FXML
    private Button bMain;
    @FXML
    private TextField tTableID;
    
    private int counter;
    private ObservableList<Table> tableList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //tableList = txtToTable();
        counter = tableList.size();
        cTableNumber.setCellValueFactory(new PropertyValueFactory("id"));        
        cOrders.setCellValueFactory(new PropertyValueFactory("table"));
        cPrice.setCellValueFactory(new PropertyValueFactory("price"));
        //tableTable.setItems(tableList);

    }    
    
    @FXML
    private void viewToMain(MouseEvent event) {
        Stage nuevaV = (Stage) mainPanel.getScene().getWindow();
        
        try {
            Parent nroot = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
            Scene scene = new Scene(nroot);
            nuevaV.setTitle("Main");
            nuevaV.setScene(scene);
            nuevaV.show();
            
        } catch (IOException ex) {
            System.getLogger(MainViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @FXML
    private void cashOut(MouseEvent event) {
        Table x = selectTable(event);
        
        double cash = x.cashOut();
        tPrice.setText("Total de la mesa "+ String.valueOf(x.getId())+": "+String.valueOf(cash));
    }

    @FXML
    private Table selectTable(MouseEvent event) {
        Table x = (Table) tableTable.getSelectionModel().getSelectedItem();
        tTableID.setText(String.valueOf(x.getId()));
        tPrice.setText("");
        return x;
    }
    
    @FXML
    private void delTable(MouseEvent event) {
        Table selected = (Table) tableTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            tableList.remove(selected);
            
            saveSystem();
            
            tTableID.setText("");
            tPrice.setText("");
        }
    }
    
    private ObservableList<Table> txtToTable() {
        ObservableList<Table> list = FXCollections.observableArrayList();
        String path = "src/txtDatabase/tables/database.txt";
        
        try (BufferedReader bw = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bw.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(";");
                
                // Crear la mesa con el ID
                int idMesa = Integer.parseInt(parts[0]);
                Table newTable = new Table(idMesa);

                // NOTA: Parsear órdenes anidadas (Mesa -> Orden -> Producto) desde una sola línea
                // es muy complejo y propenso a errores con split simple. 
                // Aquí asumimos que leemos el ID y creamos la mesa vacía o parseamos básico.
                // Si quieres persistencia completa de órdenes, recomiendo guardar IDs de órdenes
                // y cargarlas desde orders.txt, pero para este ejemplo simple:
                
                /* Aquí deberías poner tu lógica de parsing de órdenes si están en la misma línea.
                   Si la línea es "1;[]", la mesa se crea vacía.
                */
                
                // Calcular precio inicial (si hubiera órdenes cargadas)
                newTable.cashOut(); 
                list.add(newTable);
            }
        } catch (IOException e) {
            System.out.println("Error cargando mesas: " + e.getMessage());
        }
        return list;
    }
    
    private void saveSystem() {
        String path = "src/txtDatabase/tables/database.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Table t : tableList) {
                writer.write(t.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error guardando datos: " + e.getMessage());
        }
    }
    
    
    
}
