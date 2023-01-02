package be.pxl.sacramento;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PropertyWriter extends Thread {
	private Path outputFile;
	private List<Property> properties;
	
	public PropertyWriter(Path outputFile, List<Property> properties) {
		this.outputFile = outputFile;
		this.properties = properties;
	}
	
	@Override
	public void run() {
		try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
			for(Property property : properties) {
				writer.write(property.toCsv());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
