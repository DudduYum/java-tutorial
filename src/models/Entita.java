/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author rkostyuk
 */
abstract public class Entita {
    private int Id;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

   
    abstract public String getFields();
    abstract public String getValues();
}
