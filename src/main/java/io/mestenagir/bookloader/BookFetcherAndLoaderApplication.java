package io.mestenagir.bookloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mestenagir.bookloader.connection.DataStaxAstraProperties;
import io.mestenagir.bookloader.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BookFetcherAndLoaderApplication {

	public static void main(String[] args) throws IOException {
//	initFetchData();
	initPhotoShooting();
//	new DataStaxAstraProperties();
//	initPhotoUploading();
	}

	private static void initPhotoUploading() {
		PhotoUploader photoUploader = new PhotoUploader();
		photoUploader.uploadPhotos();
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
			FileReader file = new FileReader("done.json");
			Book[] books = mapper.readValue(file, Book[].class);
			ArrayList<Book> saved = new ArrayList<>(), notSaved = new ArrayList<>();
			for (Book book : books) {
				if(book.getName().contains("..>")){
					book.setName(book.getName().replace("..>",""));
					notSaved.add(book);
				} else
					saved.add(book);
			}

			mapper.writeValue(new FileWriter("saved.json",false), saved);
			mapper.writeValue(new FileWriter("notSaved.json",false), notSaved);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
