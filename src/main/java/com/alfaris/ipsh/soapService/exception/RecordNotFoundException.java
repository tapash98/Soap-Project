package com.alfaris.ipsh.soapService.exception;

public class RecordNotFoundException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3922936490817307687L;

	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordNotFoundException(String message) {
		super(message);
	}

	public RecordNotFoundException(Throwable cause) {
		super(cause);
	}
	
	 
}
