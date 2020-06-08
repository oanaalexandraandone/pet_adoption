package com.example.adoption.web.errors;

public class PasswordAlreadyUsedException extends RuntimeException {
    public PasswordAlreadyUsedException() {
    }

    public PasswordAlreadyUsedException(String message) {
        super(message);
    }

    public PasswordAlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordAlreadyUsedException(Throwable cause) {
        super(cause);
    }
}
