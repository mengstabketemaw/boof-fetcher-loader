package io.mestenagir.bookloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mestenagir.bookloader.connection.DataStaxAstraProperties;
import io.mestenagir.bookloader.model.Book;

import java.io.FileReader;
import java.io.IOException;

public class BookFetcherAndLoaderApplication {

	public static void main(String[] args) throws IOException {
//	initFetchData();
	initPhotoShooting();
//	new DataStaxAstraProperties();
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
		PdfPhoto pdfToPhoto = new PdfPhoto();
		try {
			FileReader file = new FileReader("books.json");
			Book[] books = mapper.readValue(file, Book[].class);
			pdfToPhoto.saveImageOfPdf(books);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
