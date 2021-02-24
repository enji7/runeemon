package systems.enji.runeemon;

import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Downloads and extracts a list of configured Java EE runtimes.
 * Requires no additional dependencies (TODO: we will need a dependency for JSON config...).
 * 
 * TODO:
 * - more runtimes:
 *   - Quarkus
 *   - Helidon
 *   - Micronaut
 *   - KumuluzEE
 * - default operation:
 *   - for all configured runtimes (or those given on command line)... 
 *   - if already extracted: next runtime
 *   - if already downloaded: extract
 *   - else: download and extract
 * - clean extractions
 *   - for all configured runtimes (or those given on command line)...
 *   - rm extraction (if present)
 *   - download (if not available)
 *   - extract again
 * - clean download
 *   - for all configured runtimes (or those given on command line)...
 *   - rm download and extraction (if present)
 *   - download and extract again
 * - start & stop operations ("start payara")
 *   - should also output port, and perhaps also link to management interface
 * - a Jakarte EE / MP spec covering these different runtime aspects would be nice, too...
 *
 */
public class Runeemon {

  private static final Logger LOG = Logger.getLogger(Runeemon.class.getName());
  
  public static void main(String[] args) throws Exception {
    try {
      CommandData cd = Commandy.run(args);
      Helpey.run(cd);
      List<RuntimeData> runtimeList = Confy.run(cd);
      for (RuntimeData runtimeData : runtimeList) {
        Path downloadedPackage = Fetchy.run(cd, runtimeData);
        Zippy.run(cd, downloadedPackage, runtimeData);
      }
    } catch (AppException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      System.err.printf("An error has occurred: %s, %s\n", e.getClass(), e.getMessage());
      LOG.log(Level.INFO, "", e);
    }
  }

}
