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
public class command {
    
        private ArrayList<product> command;

    public command(){
        command = new ArrayList<>();
    }

    public void setCommand(ArrayList<product> x) {
        command = x;
    }

    public ArrayList<product> getCommand() {
        return command;
    }

    public void addProductToCommand(product x){
        command.add(x);
    }
    public void delProductFromCommand(product x){
        command.remove(x);
    }

    public double priceForCommand(){
        double price = 0;

        for(product x: command){
            price += (x.getPrice()/command.size());
        }

        return price;
    }
    
}
