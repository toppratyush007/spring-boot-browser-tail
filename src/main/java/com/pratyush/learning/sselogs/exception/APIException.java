package com.pratyush.learning.sselogs.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by pratyush.k on 02/03/18.
 */
@Data
@AllArgsConstructor
public class APIException extends RuntimeException{
    private String message;
    private int code;
}
