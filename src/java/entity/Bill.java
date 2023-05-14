/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author FPT
 */
public class Bill {

    private String bid, dateCreate, recAddress, recPhone, note;
    private double totalMoney;
    private int status;
    private String cid;

    public Bill() {
    }

    public Bill(String bid, int status) {
        this.bid = bid;
        this.status = status;
    }

    public Bill(String bid, String dateCreate, String recAddress, String recPhone, String note, double totalMoney, int status, String cid) {
        this.bid = bid;
        this.dateCreate = dateCreate;
        this.recAddress = recAddress;
        this.recPhone = recPhone;
        this.note = note;
        this.totalMoney = totalMoney;
        this.status = status;
        this.cid = cid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
