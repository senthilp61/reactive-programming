package com.poc.reactiveprogramming.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NetworkException extends Throwable{

    private Throwable exception;
    private String message;
}
