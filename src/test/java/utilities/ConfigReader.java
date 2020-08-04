package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Implement a Configuration reader class that reads properties file values
 * Create a method getPropertiesValue() that take String key, parameter and returns the value of specified key.
 */
public class ConfigReader {

    private static Properties properties;

    static {

		String filePath = "src/test/resources/properties/digitalBank.properties";

		FileInputStream input;

		try {
			input = new FileInputStream(filePath);
			properties = new Properties();
			properties.load(input);
			input.close();

		} catch (IOException e) {

			System.out.println("File not found");
		}
	}

	public static String getPropertiesValue(String key) {
		return properties.getProperty(key);

	}



}
