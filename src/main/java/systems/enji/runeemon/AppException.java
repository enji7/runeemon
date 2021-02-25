package systems.enji.runeemon;

/**
 * Will be thrown in expected exceptional cases. 
 */
@SuppressWarnings("serial")
class AppException extends RuntimeException {

  AppException(String message) {
    super(message);
  }
  
  AppException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
