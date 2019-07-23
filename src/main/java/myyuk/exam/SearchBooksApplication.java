package myyuk.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SearchBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchBooksApplication.class, args);
	}

}
