package com.example.realestate.models;

public class RealEstateClass {

    private String objectID;

    private int propertyID;
    private int propertyArea;

    private String propertyLocation;

    private int propertyServices;
    private int property1stCorner;
    private int property2ndCorner;
    private int propertySingle;
    private int propertyMasjid;
    private int propertyHospital;
    private int propertyPoliceStation;
    private int propertySchool;
    private int propertyMall;
    private int propertyMainRoad;
    private int propertyBranchRoad;
    private int propertyBakery;
    private int propertyStation;
    private int propertyPharmacy;
    private int propertyPark;
    private int propertyPetrolStation;
    private int propertyVenue;
    private int propertyATM;
    private int propertySquare;
    private int propertyLaundry;
    private int propertyBarber;
    private int property1stGrade;
    private int property2ndGrade;
    private int property3rdGrade;

    private int propertyPriceUSD;
    private int propertyPriceSDG;

    private int propertySold;

    public RealEstateClass(String objectID, int propertyID, int propertyArea, String propertyLocation, int propertyServices,
                           int property1stCorner, int property2ndCorner, int propertySingle, int propertyMasjid,
                           int propertyHospital, int propertyPoliceStation, int propertySchool, int propertyMall,
                           int propertyMainRoad, int propertyBranchRoad, int propertyBakery, int propertyStation,
                           int propertyPharmacy, int propertyPark, int propertyPetrolStation, int propertyVenue,
                           int propertyATM, int propertySquare, int propertyLaundry, int propertyBarber,
                           int property1stGrade, int property2ndGrade, int property3rdGrade, int propertyPriceUSD,
                           int propertyPriceSDG, int propertySold) {

        this.objectID = objectID;
        this.propertyID = propertyID;
        this.propertyArea = propertyArea;
        this.propertyLocation = propertyLocation;
        this.propertyServices = propertyServices;
        this.property1stCorner = property1stCorner;
        this.property2ndCorner = property2ndCorner;
        this.propertySingle = propertySingle;
        this.propertyMasjid = propertyMasjid;
        this.propertyHospital = propertyHospital;
        this.propertyPoliceStation = propertyPoliceStation;
        this.propertySchool = propertySchool;
        this.propertyMall = propertyMall;
        this.propertyMainRoad = propertyMainRoad;
        this.propertyBranchRoad = propertyBranchRoad;
        this.propertyBakery = propertyBakery;
        this.propertyStation = propertyStation;
        this.propertyPharmacy = propertyPharmacy;
        this.propertyPark = propertyPark;
        this.propertyPetrolStation = propertyPetrolStation;
        this.propertyVenue = propertyVenue;
        this.propertyATM = propertyATM;
        this.propertySquare = propertySquare;
        this.propertyLaundry = propertyLaundry;
        this.propertyBarber = propertyBarber;
        this.property1stGrade = property1stGrade;
        this.property2ndGrade = property2ndGrade;
        this.property3rdGrade = property3rdGrade;
        this.propertyPriceUSD = propertyPriceUSD;
        this.propertyPriceSDG = propertyPriceSDG;
        this.propertySold = propertySold;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public int getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(int propertyArea) {
        this.propertyArea = propertyArea;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(String propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public int getPropertyServices() {
        return propertyServices;
    }

    public void setPropertyServices(int propertyServices) {
        this.propertyServices = propertyServices;
    }

    public int getProperty1stCorner() {
        return property1stCorner;
    }

    public void setProperty1stCorner(int property1stCorner) {
        this.property1stCorner = property1stCorner;
    }

    public int getProperty2ndCorner() {
        return property2ndCorner;
    }

    public void setProperty2ndCorner(int property2ndCorner) {
        this.property2ndCorner = property2ndCorner;
    }

    public int getPropertySingle() {
        return propertySingle;
    }

    public void setPropertySingle(int propertySingle) {
        this.propertySingle = propertySingle;
    }

    public int getPropertyMasjid() {
        return propertyMasjid;
    }

    public void setPropertyMasjid(int propertyMasjid) {
        this.propertyMasjid = propertyMasjid;
    }

    public int getPropertyHospital() {
        return propertyHospital;
    }

    public void setPropertyHospital(int propertyHospital) {
        this.propertyHospital = propertyHospital;
    }

    public int getPropertyPoliceStation() {
        return propertyPoliceStation;
    }

    public void setPropertyPoliceStation(int propertyPoliceStation) {
        this.propertyPoliceStation = propertyPoliceStation;
    }

    public int getPropertySchool() {
        return propertySchool;
    }

    public void setPropertySchool(int propertySchool) {
        this.propertySchool = propertySchool;
    }

    public int getPropertyMall() {
        return propertyMall;
    }

    public void setPropertyMall(int propertyMall) {
        this.propertyMall = propertyMall;
    }

    public int getPropertyMainRoad() {
        return propertyMainRoad;
    }

    public void setPropertyMainRoad(int propertyMainRoad) {
        this.propertyMainRoad = propertyMainRoad;
    }

    public int getPropertyBranchRoad() {
        return propertyBranchRoad;
    }

    public void setPropertyBranchRoad(int propertyBranchRoad) {
        this.propertyBranchRoad = propertyBranchRoad;
    }

    public int getPropertyBakery() {
        return propertyBakery;
    }

    public void setPropertyBakery(int propertyBakery) {
        this.propertyBakery = propertyBakery;
    }

    public int getPropertyStation() {
        return propertyStation;
    }

    public void setPropertyStation(int propertyStation) {
        this.propertyStation = propertyStation;
    }

    public int getPropertyPharmacy() {
        return propertyPharmacy;
    }

    public void setPropertyPharmacy(int propertyPharmacy) {
        this.propertyPharmacy = propertyPharmacy;
    }

    public int getPropertyPark() {
        return propertyPark;
    }

    public void setPropertyPark(int propertyPark) {
        this.propertyPark = propertyPark;
    }

    public int getPropertyPetrolStation() {
        return propertyPetrolStation;
    }

    public void setPropertyPetrolStation(int propertyPetrolStation) {
        this.propertyPetrolStation = propertyPetrolStation;
    }

    public int getPropertyVenue() {
        return propertyVenue;
    }

    public void setPropertyVenue(int propertyVenue) {
        this.propertyVenue = propertyVenue;
    }

    public int getPropertyATM() {
        return propertyATM;
    }

    public void setPropertyATM(int propertyATM) {
        this.propertyATM = propertyATM;
    }

    public int getPropertySquare() {
        return propertySquare;
    }

    public void setPropertySquare(int propertySquare) {
        this.propertySquare = propertySquare;
    }

    public int getPropertyLaundry() {
        return propertyLaundry;
    }

    public void setPropertyLaundry(int propertyLaundry) {
        this.propertyLaundry = propertyLaundry;
    }

    public int getPropertyBarber() {
        return propertyBarber;
    }

    public void setPropertyBarber(int propertyBarber) {
        this.propertyBarber = propertyBarber;
    }

    public int getProperty1stGrade() {
        return property1stGrade;
    }

    public void setProperty1stGrade(int property1stGrade) {
        this.property1stGrade = property1stGrade;
    }

    public int getProperty2ndGrade() {
        return property2ndGrade;
    }

    public void setProperty2ndGrade(int property2ndGrade) {
        this.property2ndGrade = property2ndGrade;
    }

    public int getProperty3rdGrade() {
        return property3rdGrade;
    }

    public void setProperty3rdGrade(int property3rdGrade) {
        this.property3rdGrade = property3rdGrade;
    }

    public int getPropertyPriceUSD() {
        return propertyPriceUSD;
    }

    public void setPropertyPriceUSD(int propertyPriceUSD) {
        this.propertyPriceUSD = propertyPriceUSD;
    }

    public int getPropertyPriceSDG() {
        return propertyPriceSDG;
    }

    public void setPropertyPriceSDG(int propertyPriceSDG) {
        this.propertyPriceSDG = propertyPriceSDG;
    }

    public String getObjectID() {
        return objectID;
    }

    public int getPropertySold() {
        return propertySold;
    }
}
