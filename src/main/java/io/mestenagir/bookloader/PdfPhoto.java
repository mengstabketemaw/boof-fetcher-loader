package io.mestenagir.bookloader;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import io.mestenagir.bookloader.connection.DataStaxAstraProperties;
import io.mestenagir.bookloader.model.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PdfPhoto {
    Logger logger = Logger.getLogger("PDF to Pic");
    public void saveImageOfPdf(Book[] books) {
        DataStaxAstraProperties data = new DataStaxAstraProperties();

        for(Book book : books){
            try {

                logger.info("Start process for -> " + book.getId());
                URL pdfURL = new URL(book.getAddress());
                PDDocument document = PDDocument.load(pdfURL.openStream());
                logger.info("Book Loaded -> " + book.getId());

                PDFRenderer pdfRenderer = new PDFRenderer(document);
                String category = book.getCategory().replace("/", "_");
                String name = book.getName().replace(".pdf","_");
                String lang = book.getLang();
                String imageName = (category + name + lang).replace(" ","_");

                ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();

/*                BufferedImage cover = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
                ImageIO.write(cover,"PNG", byteArrayStream);
                ByteBuffer coverBuffer = ByteBuffer.wrap(byteArrayStream.toByteArray());
                bufferList.add(coverBuffer);*/

                for (int page = 0; page < Math.min(10, document.getNumberOfPages()); page++) {
                    logger.info(String.format("Image writing for book (%s) STARTED-> page %d",book.getId(), page));
                    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.BINARY);
//                    ImageIO.write(bim, "PNG", new File(imageName + "_page_" + (page + 1) + ".png"));
                    ImageIO.write(bim, "PNG", byteArrayStream);
                    ByteBuffer buffer = ByteBuffer.wrap(byteArrayStream.toByteArray());
                    data.uploadPhotos(buffer, book.getId());

                    logger.info(String.format("Image writing for book (%s) DONE-> page %d",book.getId(), page));
                }
                document.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}