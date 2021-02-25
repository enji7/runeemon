package systems.enji.runeemon;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Holds configuration data for a single runtime.
 * As soon as Java 17 LTS is released, use a record instead.
 */
final class RuntimeData {

  private String name;
  private String version;
  private String downloadUrl;
  private String fileType;
  private String deploymentDir;
  private String startCommand;
  private String stopCommand; 
  private String startForegroundCommand;
  private String port;
  private String welcomePage;
  private String logDir;
  private String config;
  
  // derived attributes
  private Path downloadedPackage;
  // TODO: use either Path or File, not both
  private File extractDir;

  RuntimeData(String name, String version, String downloadUrl, String fileType, String deploymentDir, String startCommand,
      String stopCommand, String startForegroundCommand, String port, String welcomePage, String logDir, String config) {
    
    this.name = name;
    this.version = version;
    this.downloadUrl = downloadUrl;
    this.fileType = fileType;
    this.deploymentDir = deploymentDir;
    this.startCommand = startCommand;
    this.stopCommand = stopCommand;
    this.startForegroundCommand = startForegroundCommand;
    this.port = port;
    this.welcomePage = welcomePage;
    this.logDir = logDir;
    this.config = config;
    
    this.downloadedPackage = Paths.get("runtimes/downloaded", String.format("%s-%s.%s", name, version, fileType));
    this.extractDir = new File("runtimes/extracted", name + "-" + version);

  }

  /**
   * Will be used for naming the download file and extracted folder.
   */
  String getName() {
    return name;
  }

  /**
   * Will be used for naming the download file and extracted folder.
   */
  String getVersion() {
    return version;
  }

  /**
   * URL from which the runtime can be downloaded.
   */
  String getDownloadUrl() {
    return downloadUrl;
  }
  
  /**
   * Type of the download file (usually 'zip').
   */
  String getFileType() {
    return fileType;
  }

  /**
   * Directory for auto-deployments.
   */
  String getDeploymentDir() {
    return deploymentDir;
  }

  /**
   * Command that starts the runtime in the background.
   */
  String getStartCommand() {
    return startCommand;
  }

  /**
   * Command that stops the runtime (after it has been started in the background).
   */
  String getStopCommand() {
    return stopCommand;
  }

  /**
   * Command that starts the runtime in the foreground.
   */
  String getStartForegroundCommand() {
    return startForegroundCommand;
  }

  /**
   * Port under which deployed applications are available.
   */
  String getPort() {
    return port;
  }

  /**
   * URL to the welcome page.
   */
  String getWelcomePage() {
    return welcomePage;
  }

  /**
   * Directory containing log files.
   */
  String getLogDir() {
    return logDir;
  }

  /**
   * Configuration file or directory.
   */
  String getConfig() {
    return config;
  }

  /**
   * Compressed download file.
   */
  Path getDownloadedPackage() {
    return downloadedPackage;
  }

  /**
   * Direction containing the extracted runtime.
   */
  File getExtractDir() {
    return extractDir;
  }

  @Override
  public String toString() {
    return this.name;
  }

}
