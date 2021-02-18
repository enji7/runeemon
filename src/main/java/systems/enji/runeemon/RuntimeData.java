package systems.enji.runeemon;

/**
 * Holds configuration data for a single runtime.
 * As soon as Java 17 LTS is released, use a record instead.
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
  private String config;

  RuntimeData(String name, String version, String downloadUrl, String deploymentDir, String startCommand,
      String stopCommand, String startForegroundCommand, String port, String welcomePage, String logDir, String config) {
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
    this.config = config;
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

  String getDeploymentDir() {
    return deploymentDir;
  }

  String getStartCommand() {
    return startCommand;
  }

  String getPort() {
    return port;
  }

  String getStopCommand() {
    return stopCommand;
  }

  String getWelcomePage() {
    return welcomePage;
  }

  String getLogDir() {
    return logDir;
  }

  String getStartForegroundCommand() {
    return startForegroundCommand;
  }

  String getConfig() {
    return config;
  }
  
}
