package api;

import com.google.protobuf.Api;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class InvalidParameter extends ApiException{

        public InvalidParameter(String message) {
            super(message);
        }

        public InvalidParameter(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
