package com.gamebacket.vercel.app.exc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GlobalExceptions {
    private String message;
    private Throwable cause;
    private HttpStatus httpStatus;
}
