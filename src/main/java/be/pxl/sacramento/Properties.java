package be.pxl.sacramento;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Properties {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu", Locale.ENGLISH);

    private List<Property> data;

    public void readProperties(Path path) {
        data = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine(); // ignore first line
            while ((line = reader.readLine()) != null) {
                Property property = new Property();
                String[] parts = line.split(",");
                property.setStreet(parts[0]);
                property.setCity(parts[1]);
                property.setZip(parts[2]);
                property.setState(parts[3]);
                property.setBeds(Integer.parseInt(parts[4]));
                property.setBaths(Integer.parseInt(parts[5]));
                property.setSquareFeet(Integer.parseInt(parts[6]));
                property.setType(parts[7]);
                property.setSaleDate(LocalDate.parse(parts[8], FORMAT));
                property.setPrice(Double.parseDouble(parts[9]));
                property.setLatitude(Double.parseDouble(parts[10]));
                property.setLongitude(Double.parseDouble(parts[11]));
                data.add(property);
            }
            System.out.println("Properties gelezen: " + data.size());
            System.out.println(data.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Property> propertiesAbovePrice(int price) {
        return data.stream().filter(p -> p.getPrice() > price).collect(Collectors.toList());
    }

    public List<Property> propertiesForZIPCode(String zip) {
        Stream<Property> myFilteredStream = data.stream().filter(p -> p.getZip().equals(zip));
        return myFilteredStream.collect(Collectors.toList());
    }

    public List<Property> propertiesSoldAfter(LocalDate date) {
        return data.stream().filter(p -> p.getSaleDate().isAfter(date)).collect(Collectors.toList());
    }

    public List<Property> lastPropertiesSold(int amount) {
        return data.stream().sorted().limit(amount).collect(Collectors.toList());
    }

    public Property findCheapest() {
        // minstens 3 slaapkamers heeft
        // minstens een oppervlakte van 1000 square feet heeft
        // in de stad Sacramento ligt
        return data.stream()
                .filter(p -> p.getBeds() >= 3)
                .filter(p -> p.getSquareFeet() >= 1000)
                .filter(p -> p.getCity().equalsIgnoreCase("Sacramento")).min(Comparator.comparingDouble(Property::getPrice)).orElse(null);

    }

}
