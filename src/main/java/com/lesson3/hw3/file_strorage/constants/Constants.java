package com.lesson3.hw3.file_strorage.constants;

public class Constants {
    public static final String FILE_REQUEST_DELETE = "DELETE FROM FILES WHERE ID = :id";
    public static final String FILE_REQUEST_FIND_BY_ID = "SELECT * FROM FILES WHERE STORAGE = :id";

    public static final String STORAGE_REQUEST_DELETE = "DELETE FROM STORAGE WHERE ID = :id";

}
