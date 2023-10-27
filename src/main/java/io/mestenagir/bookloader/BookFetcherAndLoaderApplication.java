package io.mestenagir.bookloader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mestenagir.bookloader.model.Book;

import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class BookFetcherAndLoaderApplication {

	public static void main(String[] args) throws IOException {
		/*ObjectMapper mapper = new ObjectMapper();
		Map<String,Book> map = new HashMap<>();
		File booksJson = new File("BookWithNoImage.json");
		List<File> bookNotFound = new ArrayList<>();

		for (Book book : mapper.readValue(booksJson, Book[].class)) {
			String category = book.getCategory().replace("/", "_");
			String name = book.getName().replace(".pdf","_");
			String lang = book.getLang();
			String key = (category + name + lang).replace(" ","_");
			map.put(key, book);
		}


		File done = new File("Compressed/Undone");
		for (File image : done.listFiles()) {
			if(!image.isFile())
				continue;
			String name = image.getName();
			String key = name.substring(0, name.indexOf("_page_"));
			Book book = map.get(key);
			if(book == null){
				System.out.println("book not found for image = " + image.getName());
				bookNotFound.add(image);
				continue;
			}

			book.getPages()
					.add(image.getName());
		}

		System.out.println("Finished writing the json file, total book notfound file = " + bookNotFound.size());



		List<Book> bookWithNoImage = new ArrayList<>();
		for (Book book : map.values()) {
			if(book.getPages().size() == 0){
				bookWithNoImage.add(book);
				System.out.printf("address = %s \n",book.getAddress());
			}
		}

		System.out.println("total book with not image = " + bookWithNoImage.size());
		mapper.writeValue(new FileWriter("BookWithNoImage1.json"), bookWithNoImage);
		mapper.writeValue(new FileWriter("BookNotFound1.json"), bookNotFound);*/
//		mapper.writeValue(new FileWriter("database1.json"), map.values());
		//	initFetchData();
//			initPhotoShooting();
		//	new DataStaxAstraProperties();
		// initPhotoUploading();
		PdfPhoto pdfToPhoto = new PdfPhoto();
		pdfToPhoto.sameImageOfPdfFromDisk();
	}

	private static void initPhotoUploading() {
		PhotoUploader photoUploader = new PhotoUploader();
		photoUploader.startUploading();
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
			FileReader file = new FileReader("BookWithNoImage.json");
			Book[] books = mapper.readValue(file, Book[].class);
			pdfToPhoto.saveImageOfPdf(books);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
