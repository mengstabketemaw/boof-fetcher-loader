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
import java.io.IOException;
import java.nio.file.Path;
//
//@SpringBootApplication
//@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class BoofFetcherAndLoaderApplication {

	public static void main(String[] args) throws IOException {
//		SpringApplication.run(BoofFetcherAndLoaderApplication.class, args);
//		try {
//			Fetcher fetcher = new Fetcher();
//			fetcher.getPdfsAndWriteToFile("http://www.ethiopianorthodox.org/amharic/");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		PdfPhotor.saveImageOfPdf("http://www.ethiopianorthodox.org/amharic/Magazines/Felege%20tibebe/01%20felege%20tibebe.pdf","01 felege tibebe");
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
