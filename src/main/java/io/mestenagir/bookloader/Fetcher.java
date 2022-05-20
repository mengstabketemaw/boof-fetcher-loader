package io.mestenagir.bookloader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Fetcher {
    private final FileWriter fileWriter = new FileWriter("books",true);
    private  int counter = 0;
    private final PrintWriter printWriter = new PrintWriter(fileWriter);
    public Fetcher() throws IOException {
    }
    public  void getPdfsAndWriteToFile(String url) throws IOException {
        print("Fetching %s...", url);
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        String reverse[] = url.split("/",5);
        String catagory = reverse[reverse.length-1].replace("%20"," ");
        for(Element link : links){
            String name = link.attr("abs:href").toLowerCase();
            String dire = link.text();
            if(dire.contains("Director")){
                continue;
            }
            else if(name.endsWith("/")){
                getPdfsAndWriteToFile(link.attr("abs:href"));
            }
            else if(name.endsWith("pdf")){
                //here we write the file to the text file
                print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
                printWriter.printf("{\"id\" : \"%d\",\"address\" : \"%s\",\"name\" : \"%s\",\"category\": \"%s\"}\n",counter++,link.attr("abs:href"),trim(link.text(), 35),catagory);
                printWriter.flush();
            }
        }
    }

    private void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }


}
