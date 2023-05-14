/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DELL
 */
public class Customer {
   private String cid,cname ,username, password, address, phone ;
   private int tatus;

    @Override
    public String toString() {
        return "Customer{" + "cid=" + cid + ", cname=" + cname + ", username=" + username + ", password=" + password + ", address=" + address + ", phone=" + phone + ", tatus=" + tatus + '}';
    }

    public Customer() {
    }

    public Customer(String cid, String cname, String username, String password, String address, String phone, int tatus) {
        this.cid = cid;
        this.cname = cname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.tatus = tatus;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTatus() {
        return tatus;
    }

    public void setTatus(int tatus) {
        this.tatus = tatus;
    }
   
}
