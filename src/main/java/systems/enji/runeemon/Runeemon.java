package systems.enji.runeemon;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import systems.enji.runeemon.data.AppException;
import systems.enji.runeemon.data.CommandData;
import systems.enji.runeemon.data.RuntimeData;
import systems.enji.runeemon.zookeepers.Cleany;
import systems.enji.runeemon.zookeepers.Commandy;
import systems.enji.runeemon.zookeepers.Confy;
import systems.enji.runeemon.zookeepers.Deploy;
import systems.enji.runeemon.zookeepers.Fetchy;
import systems.enji.runeemon.zookeepers.Helpy;
import systems.enji.runeemon.zookeepers.Infy;
import systems.enji.runeemon.zookeepers.Listy;
import systems.enji.runeemon.zookeepers.Starty;
import systems.enji.runeemon.zookeepers.Zippy;

/**
 * Downloads and extracts a list of configured Java EE runtimes.
 * Requires no additional dependencies.
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
