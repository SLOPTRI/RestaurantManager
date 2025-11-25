/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author slt
 */
public class Table {
    
    private int id;
    private List<Order> table;
    private double price;
    
    public Table(int id){
        this.id = id;
        this.table = new ArrayList();
        this.price = 0;
    }

    public void setTable(List<Order> table) {
        this.table = table;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Order> getTable() {
        return table;
    }

    public double getPrice() {
        return price;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public Double cashOut(){
        for(Order x : table){
            this.price += x.priceForOrder();
        }
        return this.price;
    }
    
    public void addOrder(Order o) {
        this.table.add(o);
    }
    
    @Override
    public String toString() {
        return id + ";" + table.toString(); 
    }
}
