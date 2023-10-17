package io.mestenagir.bookloader.connection;
import com.datastax.astra.sdk.AstraClient;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.BatchableStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.internal.core.cql.DefaultSimpleStatement;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mestenagir.bookloader.PdfPhoto;
import io.mestenagir.bookloader.model.Book;
import lombok.extern.java.Log;

import java.io.File;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class DataStaxAstraProperties {
    Logger logger = Logger.getLogger("DataBase");
    CqlSession session;

    public DataStaxAstraProperties(){
        File file = new File("secure-connect-eotc-books.zip");
        DriverConfigLoader loader = DriverConfigLoader.programmaticBuilder()
                .withInt(DefaultDriverOption.REQUEST_TIMEOUT, 360000)
                .withInt(DefaultDriverOption.CONNECTION_CONNECT_TIMEOUT, 10000)
                .build();
        session = CqlSession.builder()
                .withCloudSecureConnectBundle(file.toPath())
                .withAuthCredentials("OiaNAjcZkaEoyozpdwGaimUt","+2AN0,pAMZTb6F8,boCeP98rmbAacl2DzvrmZg,+7wd3Bp,CLtGfXlXtN82lDbm5EzDy+6XjbwTLp35vv6sIanspH,Sxy7ZDGEfuiQf.x_9n-9iUNlkZ7AJ8msJJI,P8")
                .withKeyspace("books")
                .withConfigLoader(loader)
                .build();

//        initTables();
//        uploadBooks();
    }


    public void uploadPhotos(ByteBuffer buffer, String bookId){
        String insertResource = "INSERT INTO resource (id,file) VALUES (?,?)";
        String insertImage = "INSERT INTO image (book_id,resource_id) VALUES (?,?)";

        String resourceId = UUID.randomUUID().toString();
        SimpleStatement statementInsertResource = SimpleStatement.newInstance(insertResource,resourceId,buffer);
        SimpleStatement statementInsertImage = SimpleStatement.newInstance(insertImage,bookId,resourceId);

        session.execute(statementInsertImage);
        session.execute(statementInsertResource);

    }
    private void uploadBooks() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("Start book insertion");
            FileReader file = new FileReader("books.json");

            Book[] books = mapper.readValue(file, Book[].class);
            String insertCql = "INSERT INTO book (id, address, name, category, lang) VALUES(?,?,?,?,?)";

            List<BatchableStatement<?>> statementList = new ArrayList<>();
            for(Book book : books){
                SimpleStatement statement = SimpleStatement.newInstance(insertCql,book.getId(),book.getAddress(),book.getName(), book.getCategory(), book.getLang());
                statementList.add(statement);
            }

            BatchStatement batchStatements = BatchStatement.newInstance(BatchType.UNLOGGED, statementList);
            session.execute(batchStatements);
            logger.info("Book insertion is done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTables() {
        logger.info("Start table initialization");
        String createBookTable = "CREATE TABLE if not exists book (" +
                "id varchar," +
                "address varchar," +
                "name varchar," +
                "category varchar," +
                "lang varchar," +
                "PRIMARY KEY(id)" +
                ");";

        String createImageTable = "CREATE TABLE if not exists image (" +
                "book_id varchar," +
                "resource_id varchar," +
                "PRIMARY KEY (book_id, resource_id)" +
                ");";

        String createResourceTable = "CREATE TABLE if not exists resource (" +
                "id varchar," +
                "size double," +
                "type varchar," +
                "file blob," +
                "PRIMARY KEY (id)" +
                ");";
        session.execute(createBookTable);
        session.execute(createImageTable);
        session.execute(createResourceTable);
        logger.info("Table initialization is done.");

    }

}