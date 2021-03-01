package systems.enji.runeemon;

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
    print("version", runtime.getVersion());
    print("start in foreground", runtime.getStartForegroundCommand());
    print("start in background", runtime.getStartCommand());
    print("stop in background", runtime.getStopCommand());
    print("deployment directory", runtime.getDeploymentDir());
    print("welcome page", runtime.getWelcomePage());
    print("logs", runtime.getLogDir());
    print("configuration", runtime.getConfig());
    System.out.println();
  }
  
  private static void print(String message, String data) {
    if (data == null || data.isBlank()) {
      return;
    }
    int padLength = 25 - message.length();
    System.out.printf("%s:%s%s\n", message, " ".repeat(padLength), data);
  }
  
}
