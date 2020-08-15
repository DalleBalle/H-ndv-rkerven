package com.danieljensen.hndvrkerven.models;

import java.util.List;
import java.util.Map;

public class Location {

    private String address;
    private List<String> documentationColumn;
    private Map<String, List<String>> documentationData;
    private String floorplanRef;
    private String store;

    public Location() {

    }

    public Location(String address, List<String> documentationColumn, Map<String, List<String>> documentationData, String floorplanRef, String store) {
        this.address = address;
        this.documentationColumn = documentationColumn;
        this.documentationData = documentationData;
        this.floorplanRef = floorplanRef;
        this.store = store;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDocumentationData(Map<String, List<String>> documentationData) {
        this.documentationData = documentationData;
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

    public Map<String, List<String>> getDocumentationData() {
        return documentationData;
    }

    public String getFloorplanRef() {
        return floorplanRef;
    }

    public String getStore() {
        return store;
    }

    public List<String> getDocumentationColumn() {
        return documentationColumn;
    }
}
