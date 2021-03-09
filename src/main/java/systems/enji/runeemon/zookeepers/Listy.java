package systems.enji.runeemon.zookeepers;

import java.util.List;

import systems.enji.runeemon.data.CommandData;
import systems.enji.runeemon.data.RuntimeData;

/**
 * Lists runtime names.
 */
public class Listy {

  public static void run(CommandData cd, List<RuntimeData> runtimes) {
    if (!cd.getList()) {
      return;
    }
    System.out.println();
    System.out.println("Available Runtimes");
    System.out.println("==================");
    for (RuntimeData runtime : runtimes) {
      System.out.println(runtime.getName());
    }
    System.out.println();
  }
  
}
