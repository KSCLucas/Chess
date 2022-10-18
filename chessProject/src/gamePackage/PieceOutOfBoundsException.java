package gamePackage;

/**
 * 
 * @author PKamps
 *
 */
public class PieceOutOfBoundsException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public PieceOutOfBoundsException() {
    // TODO Auto-generated constructor stub
  }

  public PieceOutOfBoundsException(String message) {
    super(message);
  }

  public PieceOutOfBoundsException(Throwable cause) {
    super(cause);
  }

  public PieceOutOfBoundsException(String message, Throwable cause) {
    super(message, cause);
  }

  public PieceOutOfBoundsException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}