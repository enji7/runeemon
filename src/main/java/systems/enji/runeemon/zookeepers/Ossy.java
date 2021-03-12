package systems.enji.runeemon.zookeepers;

/**
 * Helps determining the current operating system (OS).
 */
public class Ossy {

  /**
   * Returns true if we are running under Windows, false otherwise.
   */
  public static boolean isWindows() {
    return System.getProperty("os.name", "unknown").toLowerCase().contains("win");
  }
  
}
