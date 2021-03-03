package systems.enji.runeemon;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Downloads and extracts a list of configured Java EE runtimes.
 * Requires no additional dependencies.
 * 
 * TODO:
 * - more runtimes:
 *   - WebLogic
 *
 */
public class Runeemon {

  private static final Logger LOG = Logger.getLogger(Runeemon.class.getName());
  
  public static void main(String[] args) throws Exception {
    try {
      CommandData cd = Commandy.run(args);
      Helpy.run(cd);
      List<RuntimeData> runtimes = Confy.run(cd);
      Listy.run(cd, runtimes);
      for (RuntimeData runtime : runtimes) {
        Cleany.run(cd, runtime);
        Fetchy.run(cd, runtime);
        Zippy.run(cd, runtime);
        Deploy.run(cd, runtime);
        Starty.run(cd, runtime);
        Infy.run(cd, runtime);
      }
    } catch (AppException e) {
      System.err.println(e.getMessage());
      LOG.log(Level.FINE, "", e);
    } catch (Exception e) {
      System.err.printf("An error has occurred: %s, %s\n", e.getClass(), e.getMessage());
      LOG.log(Level.INFO, "", e);
    }
  }

}
