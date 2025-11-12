package main.api_connect;

public class ApiException extends Exception {
    public int statusCode;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
