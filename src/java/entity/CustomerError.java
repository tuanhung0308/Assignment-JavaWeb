/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class CustomerError {

    private String cidError, cnameError, usernameError, passwordError, addressError, phoneError;
    private String statusError, confirm;

    public CustomerError(String cidError, String cnameError, String usernameError, String passwordError, String addressError, String phoneError, String statusError, String confirm) {
        this.cidError = cidError;
        this.cnameError = cnameError;
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.addressError = addressError;
        this.phoneError = phoneError;
        this.statusError = statusError;
        this.confirm = confirm;
    }

    public CustomerError() {
        this.cidError = "";
        this.cnameError = "";
        this.usernameError = "";
        this.passwordError = "";
        this.addressError = "";
        this.phoneError = "";
        this.statusError = "";
        this.confirm = "";
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getCidError() {
        return cidError;
    }

    public void setCidError(String cidError) {
        this.cidError = cidError;
    }

    public String getCnameError() {
        return cnameError;
    }

    public void setCnameError(String cnameError) {
        this.cnameError = cnameError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

}
