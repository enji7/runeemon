package systems.enji.runeemon.zookeepers;

import systems.enji.runeemon.data.CommandData;

/**
 * Prints usage info.
 */
public class Helpy {

  public static void run(CommandData cd) {
    
    if (!cd.getHelp()) {
      return;
    }
    
    System.out.println();
    System.out.println("Usage");
    System.out.println("=====");
    System.out.println("./runeemon");
    System.out.println("./runeemon <command> <comma-separated runtime names, or 'all'>");
    System.out.println();
    System.out.println("Commands");
    System.out.println("--------");
    System.out.println("list               lists the names of all configured runtimes");
    System.out.println("fetch              downloads the given runtimes into the 'nest' folder (if not already done)");
    System.out.println("hatch              downloads and extracts the given runtimes into the 'zoo' folder (if not already done)");
    System.out.println("deploy             deploys the WAR from the autodeploy directory to the given runtimes (fetch and hatch included)");
    System.out.println("start-bg           starts the given runtime in the background (fetch, hatch and deploy included)");
    System.out.println("start-fg           starts the given runtime in the foreground (fetch, hatch and deploy included)");
    System.out.println("start              starts the given runtime in the background or foreground (fetch, hatch and deploy included)");
    System.out.println("stop               stops the given runtime after it has been started in the background");
    System.out.println("clean-nest         removes the downloads and extractions for the given runtimes");
    System.out.println("clean-zoo          removes the uncompressed files for the given runtimes");
    System.out.println("clean-deployments  removes the deployments (WARs) for the given runtimes");
    System.out.println("info               prints available information for the given runtimes");
    System.out.println();
    System.out.println("Examples");
    System.out.println("--------");
    System.out.println("runeemon list");
    System.out.println("  -> lists the names of all configured runtimes");
    System.out.println("runeemon hatch wildfly,payara");
    System.out.println("  -> downloads and extracts Wildfly and Payara");
    System.out.println("runeemon hatch all");
    System.out.println("  -> downloads and extracts *all* configured runtimes");
    System.out.println("runeemon info wildfly");
    System.out.println("  -> prints info for Wildfly");
    System.out.println();
    
  }
  
}
