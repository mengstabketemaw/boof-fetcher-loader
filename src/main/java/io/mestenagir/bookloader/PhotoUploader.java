package io.mestenagir.bookloader;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class PhotoUploader {
    Cloudinary cloudinary = new Cloudinary("cloudinary://936985737253783:aJIBlZ8wdojwJaoPOaHrFRYDEaA@dite3j4b1");
    Map params1 = ObjectUtils.asMap(
            "use_filename", true,
            "unique_filename", false,
            "overwrite", true
    );
    public PhotoUploader() {
        cloudinary.config.secure = true;
    }

    public void uploadPhotos() {
        try {
            File files = new File("Compressed");
            for (File file : Objects.requireNonNull(files.listFiles())) {
                if(file.isFile()) {

                    System.out.println(file.getName() + " -- started uploading");
                        cloudinary.uploader()
                            .upload(file, params1);

                }
            }
        } catch (IOException ignored) {

        }
    }
}
