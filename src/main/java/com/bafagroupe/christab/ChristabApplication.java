package com.bafagroupe.christab;

import com.bafagroupe.christab.web.rest.UtilisateurRessource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

// @SpringBootApplication(exclude= {SecurityAutoConfiguration.class}) //permet de désactiver la connexion obligatoire avant tout accès des interfaces
@SpringBootApplication
// @ComponentScan(basePackageClasses = UtilisateurRessource.class)
public class ChristabApplication{ //  implements CommandLineRunner {

	/*@Autowired
	private EnginRepository enginRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(ChristabApplication.class, args);

	}

	/*@Override
	public void run(String... args) throws Exception {
		enginRepository.save(new Engin(1, "Toyota", "Auris", 2015, "En bon état", "NM", 4, "NM"));
	}*/
}
