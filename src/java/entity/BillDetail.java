/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.logging.Logger;

/**
 *
 * @author FPT
 */
public class BillDetail {
    private String bid, pid,billId;
    private int buyQuantity;
    private double buyPrice, subtotal;
    
    public BillDetail(){
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public BillDetail(String bid, String pid, String billId, int buyQuantity, double buyPrice, double subtotal) {
        this.bid = bid;
        this.pid = pid;
        this.billId = billId;
        this.buyQuantity = buyQuantity;
        this.buyPrice = buyPrice;
        this.subtotal = subtotal;
    }

    

    
    public BillDetail(String bid, String pid, int buyQuantity, double buyPrice, double subtotal) {
        this.bid = bid;
        this.pid = pid;
        this.buyQuantity = buyQuantity;
        this.buyPrice = buyPrice;
        this.subtotal = subtotal;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
   
    
}
