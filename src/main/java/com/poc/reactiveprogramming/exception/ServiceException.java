package com.poc.reactiveprogramming.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServiceException extends Throwable{

    private Throwable exception;
    private String message;
}
