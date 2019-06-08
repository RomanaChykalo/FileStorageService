package web.fault;

public class SoapServiceException extends RuntimeException {
	public SoapServiceException(String message) {
            super(message);
        }
}
