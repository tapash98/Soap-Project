package com.alfaris.ipsh.soapService.exception;

public class RecordCreateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6653546511433490087L;

	public RecordCreateException() {
		super();
	}

	public RecordCreateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RecordCreateException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordCreateException(String message) {
		super(message);
	}

	public RecordCreateException(Throwable cause) {
		super(cause);
	}
	

}
