package systems.enji.runeemon;

/**
 * Holds configuration data for a single runtime.
 * TODO: read from JSON config (more readable)
 * TODO: switch to a newer Java version and use a record instead.
 */
final class RuntimeData {

  private String name;
  private String version;
  private String downloadUrl;
  private String deploymentDir;
  private String startCommand;
  private String stopCommand; 
  private String startForegroundCommand;
  private String port;
  private String welcomePage;
  private String logDir;

  RuntimeData(String name, String version, String downloadUrl, String deploymentDir, String startCommand,
      String stopCommand, String startForegroundCommand, String port, String welcomePage, String logDir) {
    this.name = name;
    this.version = version;
    this.downloadUrl = downloadUrl;
    this.deploymentDir = deploymentDir;
    this.startCommand = startCommand;
    this.stopCommand = stopCommand;
    this.startForegroundCommand = startForegroundCommand;
    this.port = port;
    this.welcomePage = welcomePage;
    this.logDir = logDir;
  }

  String getName() {
    return name;
  }
  
  String getVersion() {
    return version;
  }

  String getDownloadUrl() {
    return downloadUrl;
  }

  public String getDeploymentDir() {
    return deploymentDir;
  }

  public String getStartCommand() {
    return startCommand;
  }

  public String getPort() {
    return port;
  }

  public String getStopCommand() {
    return stopCommand;
  }

  public String getWelcomePage() {
    return welcomePage;
  }

  public String getLogDir() {
    return logDir;
  }

  public String getStartForegroundCommand() {
    return startForegroundCommand;
  }
  
}
