package com.poc.reactiveprogramming.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieException extends Throwable{

    private Throwable exception;
    private String message;
}
