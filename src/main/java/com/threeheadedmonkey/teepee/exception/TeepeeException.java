package com.threeheadedmonkey.teepee.exception;

/**
 * Exceptions created inside Teepee
 */
public class TeepeeException extends RuntimeException {

    public TeepeeException(String message, Throwable cause) {
        super(message, cause);
    }
}
