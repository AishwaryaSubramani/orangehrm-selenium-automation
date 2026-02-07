package Utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:\\Users\\aishw\\eclipse-workspace\\OrangeHRMAutomationDemo\\src\\test\\resources\\Configfiles\\config.properties");
		
		Properties p = new Properties();
		
		p.load(fr);
        System.out.println(p.getProperty("browser"));
        System.out.println(p.getProperty("testurl"));

	}

}
