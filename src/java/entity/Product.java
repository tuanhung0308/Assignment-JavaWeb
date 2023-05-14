/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DELL
 */
public class Product {

    private String pid, pname;
    private int quantity;
    private double price;
    private String image;
    private String description;
    private int status, cateId;
    private int quantityAvaiable;

    public Product() {
    }

    public Product(String pid, String pname, int quantity, double price, String image, String description, int status, int cateId) {
        this.pid = pid;
        this.pname = pname;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.description = description;
        this.status = status;
        this.cateId = cateId;
    }

    public Product(String pid, String pname, int quantity, double price, String image, String description, int status, int cateId, int quantityAvaiable) {
        this.pid = pid;
        this.pname = pname;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.description = description;
        this.status = status;
        this.cateId = cateId;
        this.quantityAvaiable = quantityAvaiable;
    }

    public int getQuantityAvaiable() {
        return quantityAvaiable;
    }

    public void setQuantityAvaiable(int quantityAvaiable) {
        this.quantityAvaiable = quantityAvaiable;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

}
