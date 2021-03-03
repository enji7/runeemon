package systems.enji.runeemon;

import java.util.List;

class Listy {

  /**
   * Lists the names of the given runtimes.
   */
  static void run(CommandData cd, List<RuntimeData> runtimes) {
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
