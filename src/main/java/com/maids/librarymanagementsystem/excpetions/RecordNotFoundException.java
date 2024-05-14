package com.maids.librarymanagementsystem.excpetions;
/**
 * @author mahmoudrezk514@gmail.com
 * @implNote class to handle duplicates of unique values
 */
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}
