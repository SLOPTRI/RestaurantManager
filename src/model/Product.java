/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author slt
 */
public class Product {
    
    private String name;
    private double price;
    private int quantity;

    public Product(String nameX, double priceX, int quantityX){
        name = nameX;
        price = priceX;
        quantity = quantityX;
    }

    public void setName(String nameX){
        name = nameX;
    }
    public void setPrice(double priceX){
        price = priceX;
    }

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString(){
        return getName() + "/" + getPrice() + "/"+ getQuantity();
    }
    
}
