package io.mestenagir.bookloader.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Book {
    String id;
    String address;
    String name;
    String category;
    String lang;
    List<String> pages = new ArrayList<>();

    @Override
    public String toString() {
        return "INSERT INTO book (id, address, name, category, lang)" +
                "VALUES (\"" + id + "\",\"" + address + "\",\""+ name + "\",\"" + category + "\",\"" + lang+"\");";
    }
}
