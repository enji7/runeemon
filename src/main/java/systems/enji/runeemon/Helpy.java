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
    System.out.println("list: lists the names of all configured runtimes");
    System.out.println("fetch: downloads the given runtimes (if not already done)");
    System.out.println("extract: downloads and extracts the given runtimes (if not already done)");
    System.out.println("clean-downloads: removes the downloads and extractions for the given runtimes");
    System.out.println("clean-extracts: removes the uncompressed files for the given runtimes");
    System.out.println();
    System.out.println("Examples");
    System.out.println("--------");
    System.out.println("./runeemon: downloads and extracts all runtimes, and prints their usage infos");
    System.out.println("./runeemon list: lists the names of all configured runtimes");
    System.out.println("./runeemon extract wildfly,payara: downloads and extracts Wildfly and Payara");
    System.out.println("./runeemon extract all: downloads and extracts all configured runtimes");
    
  }
  
}
