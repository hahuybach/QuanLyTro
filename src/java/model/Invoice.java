/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Bach
 */
public class Invoice {
    private int month;
    private int year;
    private int old_electric_num;
    private int electric_num;
    private int electric_price;
    private int old_water_num;
    private int water_num;
    private int water_price;
    private int service_price;
    private int cleaning_price;
    private int bike_num;
    private int bike_price;
    private int bike_fee;
    private int cable_tv_price;
    private int internet_price;
    private int total;
    private int paid;
    private int debt;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOld_electric_num() {
        return old_electric_num;
    }

    public void setOld_electric_num(int old_electric_num) {
        this.old_electric_num = old_electric_num;
    }

    public int getElectric_num() {
        return electric_num;
    }

    public void setElectric_num(int electric_num) {
        this.electric_num = electric_num;
    }

    public int getElectric_price() {
        return electric_price;
    }

    public void setElectric_price(int electric_price) {
        this.electric_price = electric_price;
    }

    public int getOld_water_num() {
        return old_water_num;
    }

    public void setOld_water_num(int old_water_num) {
        this.old_water_num = old_water_num;
    }

    public int getWater_num() {
        return water_num;
    }

    public void setWater_num(int water_num) {
        this.water_num = water_num;
    }

    public int getWater_price() {
        return water_price;
    }

    public void setWater_price(int water_price) {
        this.water_price = water_price;
    }

    public int getService_price() {
        return service_price;
    }

    public void setService_price(int service_price) {
        this.service_price = service_price;
    }

    public int getCleaning_price() {
        return cleaning_price;
    }

    public void setCleaning_price(int cleaning_price) {
        this.cleaning_price = cleaning_price;
    }

    public int getBike_num() {
        return bike_num;
    }

    public void setBike_num(int bike_num) {
        this.bike_num = bike_num;
    }

    public int getBike_price() {
        return bike_price;
    }

    public void setBike_price(int bike_price) {
        this.bike_price = bike_price;
    }

    public int getBike_fee() {
        return bike_fee;
    }

    public void setBike_fee(int bike_fee) {
        this.bike_fee = bike_fee;
    }

    public int getCable_tv_price() {
        return cable_tv_price;
    }

    public void setCable_tv_price(int cable_tv_price) {
        this.cable_tv_price = cable_tv_price;
    }

    public int getInternet_price() {
        return internet_price;
    }

    public void setInternet_price(int internet_price) {
        this.internet_price = internet_price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt() {
        this.debt = total-paid;
    }
    
    
}
