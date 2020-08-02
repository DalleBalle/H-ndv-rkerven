package com.danieljensen.hndvrkerven.models;

public class Location {

    private String address;
    private String detailTable1;
    private String floorplanRef;
    private String store;

    public Location() {

    }

    public Location(String address, String detailTable1, String floorplanRef, String store) {
        this.address = address;
        this.detailTable1 = detailTable1;
        this.floorplanRef = floorplanRef;
        this.store = store;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDetailTable1(String detailTable1) {
        this.detailTable1 = detailTable1;
    }

    public void setFloorplanRef(String floorplanRef) {
        this.floorplanRef = floorplanRef;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getAddress() {
        return address;
    }

    public String getDetailTable1() {
        return detailTable1;
    }

    public String getFloorplanRef() {
        return floorplanRef;
    }

    public String getStore() {
        return store;
    }
}
