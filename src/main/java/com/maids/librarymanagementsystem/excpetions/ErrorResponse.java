package com.maids.librarymanagementsystem.excpetions;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @author mahmoudrezk514@gmail.com
 * @implNote pojo class which represents a returned handled errors
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Boolean success;
    private String message;
    private LocalDateTime dateTime;
    private List<String> details;

    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
        this.success =Boolean.FALSE;
        this.dateTime = LocalDateTime.now();
    }
}
