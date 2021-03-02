package systems.enji.runeemon;

/**
 * Prints usage info.
 */
class Helpy {

  static void run(CommandData cd) {
    
    if (!cd.getHelp()) {
      return;
    }
    
    System.out.println("Usage");
    System.out.println("=====");
    System.out.println("./runeemon");
    System.out.println("./runeemon <command> <comma-separated runtime names, or 'all'>");
    System.out.println();
    System.out.println("Commands");
    System.out.println("--------");
    System.out.println("list:               lists the names of all configured runtimes");
    System.out.println("fetch:              downloads the given runtimes (if not already done)");
    System.out.println("extract:            downloads and extracts the given runtimes (if not already done)");
    System.out.println("deploy:             deploys the WAR from the autodeploy directory to the given runtimes (fetch and extract included)");
    System.out.println("start-bg:           starts the given runtime in the background (fetch, extract and deploy included)");
    System.out.println("start-fg:           starts the given runtime in the foreground (fetch, extract and deploy included)");
    System.out.println("start:              starts the given runtime in the background or foreground (fetch, extract and deploy included)");
    System.out.println("stop:               stops the given runtime after it has been started in the background");
    System.out.println("clean-downloads:    removes the downloads and extractions for the given runtimes");
    System.out.println("clean-extracts:     removes the uncompressed files for the given runtimes");
    System.out.println("clean-deployments:  removes the deployments (WARs) for the given runtimes");
    System.out.println("info:               prints available information for the given runtimes");
    System.out.println();
    System.out.println("Examples");
    System.out.println("--------");
    System.out.println("./runeemon: downloads and extracts all runtimes, and prints their usage infos");
    System.out.println("./runeemon list: lists the names of all configured runtimes");
    System.out.println("./runeemon extract wildfly,payara: downloads and extracts Wildfly and Payara");
    System.out.println("./runeemon extract all: downloads and extracts all configured runtimes");
    
  }
  
}
