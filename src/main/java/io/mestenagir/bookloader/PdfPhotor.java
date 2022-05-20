package io.mestenagir.bookloader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PdfPhotor {
    public static void saveImageOfPdf(String url,String name) {
        System.out.println("=======================================================");
        System.out.println("taking picture of the file "+name);
        String fileName = name.replace(".pdf","");
        try {
            String destinationDir = "C:\\Users\\Ketemaw\\Desktop\\boof-fetcher-and-loader\\src\\main\\resources\\images\\"; // converted images from pdf document are saved here
            File destinationFile = new File(destinationDir);
            if (!destinationFile.exists()) {
                destinationFile.mkdir();
                System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
            }
            InputStream is = new URL(url).openStream();
            PDDocument document = PDDocument.load(is);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int numberOfPages = document.getNumberOfPages();
            System.out.println("Total files to be converting -> " + numberOfPages);
            String fileExtension = "png"; /*                  * 600 dpi give good image clarity but size of each image is 2x times of 300 dpi.                  * Ex:  1. For 300dpi 04-Request-Headers_2.png expected size is 797 KB                  *      2. For 600dpi 04-Request-Headers_2.png expected size is 2.42 MB                  */
            int dpi = 300; // use less dpi for to save more space in harddisk. For professional usage you can use more than 300dpi
            File outPutFile = new File(destinationDir + fileName + "." + fileExtension);
            BufferedImage bImage = pdfRenderer.renderImageWithDPI(0, dpi, ImageType.RGB);
            ImageIO.write(bImage, fileExtension, outPutFile);
            document.close();
            System.out.println("Converted Images are saved at -> " + destinationFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}