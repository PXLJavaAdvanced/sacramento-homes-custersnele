package be.pxl.sacramento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Property implements Comparable<Property> {
    private String street;
    private String city;
    private String zip;
    private String state;
    private int beds;
    private int baths;
    private int squareFeet;
    private String type;
    private LocalDate saleDate;
    private double price;
    private double latitude;
    private double longitude;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBaths() {
        return baths;
    }

    public void setBaths(int baths) {
        this.baths = baths;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Property(String street, String city, String zip, String state, int beds, int baths, int squareFeet, String type,
                    LocalDate saleDate, double price, double latitude, double longitude) {
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.beds = beds;
        this.baths = baths;
        this.squareFeet = squareFeet;
        this.type = type;
        this.saleDate = saleDate;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Property() {

    }

    @Override
    public String toString() {
        return "Property [street=" + street + ", city=" + city + ", zip=" + zip + ", state=" + state + ", beds=" + beds
                + ", baths=" + baths + ", squareFeet=" + squareFeet + ", type=" + type + ", saleDate=" + saleDate + ", price="
                + price + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    public String toCsv() {
        return street + "," + city + "," + zip + "," + state + "," + beds + "," + baths + "," + squareFeet + "," + type + "," + Properties.FORMAT.format(ZonedDateTime.of(saleDate, LocalTime.of(0, 0), ZoneId.of("EST5EDT"))) + "," +
                "," + price + "," + latitude + "," + longitude;
    }

    @Override
    public int compareTo(Property otherProperty) {
        return otherProperty.saleDate.compareTo(saleDate);
    }
}
