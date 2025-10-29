/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author slt
 */
public class product {
    
    private String name;
    private double price;

    public product(String nameX, double priceX){
        name = nameX;
        price = priceX;
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

    public String toString(){
        return getName() + "/" + getPrice();
    }
    
}
