package com.maids.librarymanagementsystem.excpetions;
/**
 * @author mahmoudrezk514@gmail.com
 * @implNote class to handle borrowing unreturned book
 */
public class DuplicateBorrowingException extends RuntimeException{
    public DuplicateBorrowingException(String message) {
        super(message);
    }
}
