/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author slt
 */
public class Order {
    
    
        private int id;
        private ArrayList<Product> order;
        

    public Order(int newId){
        order = new ArrayList<>();
        id = newId;
    }
    
    public Order(int newId, ArrayList<Product> productList){
        id = newId;
        order = productList;
    }
    
    public void setId(int newId){
        id = newId;
    }
    
    public int getId(){
        return id;
    }
    
    public void setOrder(ArrayList<Product> x) {
        order = x;
    }

    public ArrayList<Product> getOrder() {
        return order;
    }

    public void addProductToOrder(Product x){
        order.add(x);
    }
    public void delProductFromOrder(Product x){
        order.remove(x);
    }

    public double priceForOrder(){
        double price = 0;

        for(Product x: order){
            price += (x.getPrice()/order.size());
        }

        return price;
    }
    
    public String toString(){
        return id + ";" + order;
    }
    
}
