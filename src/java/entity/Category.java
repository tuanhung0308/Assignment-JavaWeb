/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DELL
 */
public class Category {

    private int cateId;
    private String cateName;
    private int tatus;

    public Category() {
    }

    public Category(int cateId, String cateName, int tatus) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.tatus = tatus;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getTatus() {
        return tatus;
    }

    public void setTatus(int tatus) {
        this.tatus = tatus;
    }

}
