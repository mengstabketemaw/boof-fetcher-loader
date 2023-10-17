package io.mestenagir.bookloader.model;


import lombok.Data;

@Data
public class Book {
    String id;
    String address;
    String name;
    String category;
    String lang;

    @Override
    public String toString() {
        return "INSERT INTO book (id, address, name, category, lang)" +
                "VALUES (\"" + id + "\",\"" + address + "\",\""+ name + "\",\"" + category + "\",\"" + lang+"\");";
    }
}
