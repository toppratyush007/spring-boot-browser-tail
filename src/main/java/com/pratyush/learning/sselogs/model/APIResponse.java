package com.pratyush.learning.sselogs.model;

import com.pratyush.learning.sselogs.exception.APIException;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by pratyush.k on 02/03/18.
 */
@Data
@AllArgsConstructor
public class APIResponse {
    private String message;
    private int status;

    public APIResponse(APIException apiEx) {
        this.message = apiEx.getMessage();
        this.status = apiEx.getCode();
    }
}
