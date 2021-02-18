package systems.enji.runeemon;

import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Downloads and extracts a list of configured Java EE runtimes.
 * Requires no additional dependencies.
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
      for (RuntimeData runtimeData : Confy.getRuntimeList()) {
        Path downloadedPackage = Fetchy.download(runtimeData);
        Zippy.unzip(downloadedPackage, runtimeData);
      }
    } catch (Exception e) {
      System.err.printf("An error has occurred: %s, %s", e.getClass(), e.getMessage());
      LOG.log(Level.INFO, "", e);
    }
  }

}