package com.pratyush.learning.sselogs.helper;

import com.pratyush.learning.sselogs.exception.APIException;
import org.springframework.http.HttpStatus;

import java.io.File;

/**
 * Created by pratyush.k on 02/03/18.
 */
public class FileReadHelper {

    public static void checkFile(String fileName) {
        File file = new File(fileName);

        if(!file.exists())
            throw new APIException("File does not exist.", HttpStatus.NOT_FOUND.value());
        if(!file.canRead())
            throw new APIException("File can't be read, possible permission issues.", HttpStatus.FORBIDDEN.value());
        if(file.isDirectory())
            throw new APIException("File is a directory.", HttpStatus.BAD_REQUEST.value());

    }
}
