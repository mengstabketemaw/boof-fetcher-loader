package io.mestenagir.bookloader;
//import io.mestenagir.bookloader.connection.DataStaxAstraProperties;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;

//import javax.annotation.PostConstruct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

//
//@SpringBootApplication
//@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class BoofFetcherAndLoaderApplication {

	public static void main(String[] args) throws IOException {
//		SpringApplication.run(BoofFetcherAndLoaderApplication.class, args);
//	initFetchData();
	initPhotoShooting();
	}

	public static void  initFetchData(){
		try {
			Fetcher fetcher = new Fetcher();
			fetcher.getPdfsAndWriteToFile("http://www.ethiopianorthodox.org/amharic/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initPhotoShooting(){
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("screen shoot the first page of book in book file");
		try {
			FileReader fileReader = new FileReader("books");
			BufferedReader reader = new BufferedReader(fileReader);
			reader.lines()
					.forEach(line->{
						try {
							Map<String,String> map = mapper.readValue(line,Map.class);
							PdfPhotor.saveImageOfPdf(map.get("address"),map.get("name"));
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
					});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//		PdfPhotor.saveImageOfPdf("http://www.ethiopianorthodox.org/amharic/Magazines/Felege%20tibebe/01%20felege%20tibebe.pdf","01 felege tibebe");
	}

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
}
