package be.pxl.sacramento;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class RealEstateApp {

	public static void main(String[] args) {
		Properties myProperties = new Properties();
		myProperties.readProperties(Paths.get("src/main/resources/sacramento_homes.csv"));

		System.out.println("**** Properties above 800000");
		List<Property> propertiesAbovePrice = myProperties.propertiesAbovePrice(800000);
		System.out.println("Number: " + propertiesAbovePrice.size());
		propertiesAbovePrice.forEach(System.out::println);

		System.out.println("**** Properties for zip: 95650");
		myProperties.propertiesForZIPCode("95650").forEach(System.out::println);

		LocalDate date = LocalDate.of(2008, 5, 20);
		System.out.println("**** Properties sold after " + date);
		myProperties.propertiesSoldAfter(date).forEach(System.out::println);
		
		System.out.println("**** Laatst verkochte ");
		myProperties.lastPropertiesSold(5).forEach(System.out::println);

		System.out.println("**** Cheapest");
		System.out.println(myProperties.findCheapest());
		
		new PropertyWriter(Paths.get("src/main/resources/output.txt"), myProperties.lastPropertiesSold(10)).start();
	}

}
