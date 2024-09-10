package de.sp.Datenbankmodellierung_Shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"de.sp.Datenbankmodellierung_Shop"})
public class DatenbankmodellierungShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatenbankmodellierungShopApplication.class, args);
	}

}
