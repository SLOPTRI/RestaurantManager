/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author slt
 */
import java.util.ArrayList;

public class table {

    private ArrayList<command> commandList;
    private int tableNumber;

    public table(int n){
        commandList = new ArrayList<>();
        tableNumber = n;
    }

    public void setCommandList(ArrayList<command> x){
        commandList = x;
    }
    public void setTableNumber(int n){
        tableNumber = n;
    }

    public int getTableNumber(){
        return tableNumber;
    }
    public ArrayList<command> getCommandList(){
        return commandList;
    }

    public double cashOut(){
        double price = 0;

        for(command x : commandList){
            price += x.priceForCommand();
        }

        return price;
    }
}
