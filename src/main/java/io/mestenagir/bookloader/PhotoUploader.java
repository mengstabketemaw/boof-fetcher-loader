package io.mestenagir.bookloader;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PhotoUploader {
    ObjectMapper mapper = new ObjectMapper();
    Cloudinary cloudinary = new Cloudinary("cloudinary://936985737253783:aJIBlZ8wdojwJaoPOaHrFRYDEaA@dite3j4b1");
    Map params1 = ObjectUtils.asMap(
            "use_filename", true,
            "unique_filename", false,
            "overwrite", true
    );
    public PhotoUploader() {
        cloudinary.config.secure = true;

    }
    public void startUploading() {
        uploadToCloud();
        retryFailedRequest(); // Second time to retry again for failed requests
    }

    private void retryFailedRequest() {
        System.out.println("Retrying  . . .");
        List<File> notFoundList = new ArrayList<>();

        try {
            File unsuccessful = new File("Unsuccessful.json");
            Set<File> unsuccessfulList = mapper.readValue(unsuccessful, new TypeReference<>() {
            });

            for (File file : unsuccessfulList){
                try {
                    upload(file);
                } catch (Exception e) {
                    notFoundList.add(file);
                    e.printStackTrace();
                    Thread.sleep(15000);
                }
            }
            System.out.println("Unsuccessful list  . . . " +notFoundList.size());
            mapper.writeValue(new FileWriter("Unsuccessful.json",false), notFoundList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void uploadToCloud() {
        List<File> notFoundList = new ArrayList<>();
        int counter = 0;

        File files = new File("Compressed/Ready");
        try {

            for(File file : files.listFiles()){
                try {
                   upload(file);
                   counter++;
                } catch (Exception e) {
                    notFoundList.add(file);
                    e.printStackTrace();
                    Thread.sleep(15000);
                }
            }
            System.out.println("successful   size = " + counter);
            System.out.println("unsuccessful size = " + notFoundList.size());
            mapper.writeValue(new FileWriter("Unsuccessful.json",false), notFoundList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void upload(File file) throws IOException {

        System.out.printf("Uploading %.2fMB file%n",(double) file.length() /1000000);
        cloudinary.uploader()
                .upload(file, params1);
    }


}
