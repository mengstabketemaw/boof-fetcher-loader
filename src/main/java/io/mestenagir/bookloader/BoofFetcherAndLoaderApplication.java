package io.mestenagir.bookloader;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

//@SpringBootApplication
public class BoofFetcherAndLoaderApplication {

	public static void main(String[] args) throws IOException {
//		SpringApplication.run(BoofFetcherAndLoaderApplication.class, args);
		var fetcher = new Fetcher();
		fetcher.getPdfsAndWriteToFile("http://www.ethiopianorthodox.org/amharic/");
	}

}
