package io.mestenagir.bookloader;
import java.io.IOException;

public class BoofFetcherAndLoaderApplication {

	public static void main(String[] args) throws IOException {
		try {
			Fetcher fetcher = new Fetcher();
			fetcher.getPdfsAndWriteToFile("http://www.ethiopianorthodox.org/amharic/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
