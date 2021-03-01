package systems.enji.runeemon;

import java.io.File;

class Infy {

  /**
   * Prints available information for the given runtime.
   */
  static void run(CommandData cd, RuntimeData runtime) {
    
    if (!cd.getInfo()) {
      return;
    }
    
    System.out.println();
    System.out.println(runtime.getName().substring(0, 1).toUpperCase() + runtime.getName().substring(1));
    System.out.println("=".repeat(runtime.getName().length()));
    
    print("version", runtime.getVersion(), null);
    print("start in foreground", runtime.getStartForegroundCommand(), runtime.getExtractDir());
    print("start in background", runtime.getStartCommand(), runtime.getExtractDir());
    print("stop in background", runtime.getStopCommand(), runtime.getExtractDir());
    print("deployment directory", runtime.getDeploymentDir(), runtime.getExtractDir());
    print("welcome page", runtime.getWelcomePage(), null);
    print("logs", runtime.getLogDir(), runtime.getExtractDir());
    print("configuration", runtime.getConfig(), runtime.getExtractDir());
    
    System.out.println();
    
  }
  
  private static void print(String title, String data, File dataPrefix) {
    if (data == null || data.isBlank()) {
      return;
    }
    if (dataPrefix != null) {
      data = new File(dataPrefix, data).getAbsolutePath();
    }
    int padLength = 25 - title.length();
    System.out.printf("%s:%s%s\n", title, " ".repeat(padLength), data);
  }
  
}
