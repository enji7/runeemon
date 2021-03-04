package systems.enji.runeemon.data;

/**
 * Will be thrown in expected exceptional cases. 
 */
@SuppressWarnings("serial")
public class AppException extends RuntimeException {

  public AppException(String message) {
    super(message);
  }
  
  public AppException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
