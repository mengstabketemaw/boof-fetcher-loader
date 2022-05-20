package io.mestenagir.bookloader;
<<<<<<< HEAD
import java.io.IOException;

public class BoofFetcherAndLoaderApplication {

	public static void main(String[] args) throws IOException {
=======
//import io.mestenagir.bookloader.connection.DataStaxAstraProperties;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;

//import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
//
//@SpringBootApplication
//@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class BoofFetcherAndLoaderApplication {

	public static void main(String[] args) throws IOException {
//		SpringApplication.run(BoofFetcherAndLoaderApplication.class, args);
>>>>>>> dd59682e40225cd0c0565c002582066ab7fdf6a8
		try {
			Fetcher fetcher = new Fetcher();
			fetcher.getPdfsAndWriteToFile("http://www.ethiopianorthodox.org/amharic/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This is necessary to have the Spring Boot app use the Astra secure bundle
	 * to connect to the database
	 */

<<<<<<< HEAD
=======
//	@Bean
//	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
//		Path bundle = astraProperties.getSecureConnectBundle().toPath();
//		return builder -> builder.withCloudSecureConnectBundle(bundle);
//	}
//
//	@PostConstruct
//	public void fetchingData(){
//		initFetchData(); // fetch the all the book name.
////		initAddData(); //add each books in to the database.
//	}

//	private void initFetchData() {
//
//	}
//
//	private void initAddData() {
//		//here we read each line and add each object to the book.
//	}

>>>>>>> dd59682e40225cd0c0565c002582066ab7fdf6a8
}
