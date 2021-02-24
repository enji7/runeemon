package systems.enji.runeemon;

/**
 * Will be thrown in expected exceptional cases. 
 */
class AppException extends RuntimeException {

  AppException(String message) {
    super(message);
  }
  
}
