package systems.enji.runeemon;

import java.util.List;

class Listy {

  static void run(CommandData cd, List<RuntimeData> runtimes) {
    if (!cd.getList()) {
      return;
    }
    System.out.println("Available Runtimes");
    System.out.println("==================");
    for (RuntimeData runtime : runtimes) {
      System.out.println(runtime.getName());
    }
  }
  
}
