package gamePackage;

/**
 * 
 *@author Lucas Noack
 *
 */
public class OnlyOneWinnerException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public OnlyOneWinnerException() {
    // TODO Auto-generated constructor stub
  }

  public OnlyOneWinnerException(String message) {
    super(message);
  }

  public OnlyOneWinnerException(Throwable cause) {
    super(cause);
  }

  public OnlyOneWinnerException(String message, Throwable cause) {
    super(message, cause);
  }

  public OnlyOneWinnerException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}


