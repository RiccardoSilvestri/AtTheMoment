package com.example.atthemoment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Fermata {
    @SerializedName("Code")
    private String code;
    @SerializedName("Description")
    private String description;
    @SerializedName("Location")
    private Location location;
    @SerializedName("CustomerCode")
    private String customerCode;
    @SerializedName("Municipality")
    private String municipality;
    @SerializedName("Address")
    private String address;
    @SerializedName("Telephone")
    private String telephone;
    @SerializedName("Fax")
    private String fax;
    @SerializedName("SiteUrl")
    private String siteUrl;
    @SerializedName("Email")
    private String email;
    @SerializedName("Category")
    private Category category;
    @SerializedName("Details")
    private Object details;
    @SerializedName("Dynamic_First_Level")
    private Object dynamicFirstLevel;
    @SerializedName("Lines")
    private List<LineInfo> lines;
    private Boolean pointAccessible;
    private Object pointStopPath;
    private Object pointStopStatus;
    private Object pointStopInfo;
    private List<Link> links;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Object getDynamicFirstLevel() {
        return dynamicFirstLevel;
    }

    public void setDynamicFirstLevel(Object dynamicFirstLevel) {
        this.dynamicFirstLevel = dynamicFirstLevel;
    }

    public List<LineInfo> getLines() {
        return lines;
    }

    public void setLines(List<LineInfo> lines) {
        this.lines = lines;
    }

    public Boolean getPointAccessible() {
        return pointAccessible;
    }

    public void setPointAccessible(Boolean pointAccessible) {
        this.pointAccessible = pointAccessible;
    }

    public Object getPointStopPath() {
        return pointStopPath;
    }

    public void setPointStopPath(Object pointStopPath) {
        this.pointStopPath = pointStopPath;
    }

    public Object getPointStopStatus() {
        return pointStopStatus;
    }

    public void setPointStopStatus(Object pointStopStatus) {
        this.pointStopStatus = pointStopStatus;
    }

    public Object getPointStopInfo() {
        return pointStopInfo;
    }

    public void setPointStopInfo(Object pointStopInfo) {
        this.pointStopInfo = pointStopInfo;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
