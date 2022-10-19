package com.koerber.ausbildung.chess.utility;

/**
 * @author Lucas Noack
 */
public class WrongSizeException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public WrongSizeException() {
    // TODO Auto-generated constructor stub
  }

  public WrongSizeException(String message) {
    super(message);
  }

  public WrongSizeException(Throwable cause) {
    super(cause);
  }

  public WrongSizeException(String message, Throwable cause) {
    super(message, cause);
  }

  public WrongSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
