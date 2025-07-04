package com.poc.reactiveprogramming.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieInfoException extends Throwable{

    private Throwable exception;
    private String message;
}
