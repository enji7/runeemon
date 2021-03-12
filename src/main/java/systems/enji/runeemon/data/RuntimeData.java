package systems.enji.runeemon.data;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import systems.enji.runeemon.zookeepers.Ossy;

/**
 * Holds configuration data for a single runtime.
 * As soon as Java 17 LTS is released, use a record instead.
 */
public final class RuntimeData {

  private String name;
  private String version;
  private String downloadUrl;
  private String fileType;
  private String deploymentDir;
  private String startCommandLinux;
  private String stopCommandLinux; 
  private String startForegroundCommandLinux;
  private String startCommandWindows;
  private String stopCommandWindows; 
  private String startForegroundCommandWindows;
  private String port;
  private String welcomePage;
  private String logDir;
  private String config;
  
  // derived attributes
  private Path downloadedPackage;
  private File extractDir;

  /**
   * Even though this constructor is huge, identifying the parameters is not problem with IDE support.
   * The alternative would have been a builder, which is overkill for internal usage.
   */
  public RuntimeData(String name, String version, String downloadUrl, String fileType, String deploymentDir,
      String startCommandLinux, String stopCommandLinux, String startForegroundCommandLinux, String startCommandWindows,
      String stopCommandWindows, String startForegroundCommandWindows, String port, String welcomePage, String logDir,
      String config) {
    
    this.name = name;
    this.version = version;
    this.downloadUrl = downloadUrl;
    this.fileType = fileType;
    this.deploymentDir = deploymentDir;
    this.startCommandLinux = startCommandLinux;
    this.stopCommandLinux = stopCommandLinux;
    this.startForegroundCommandLinux = startForegroundCommandLinux;
    this.startCommandWindows = startCommandWindows;
    this.stopCommandWindows = stopCommandWindows;
    this.startForegroundCommandWindows = startForegroundCommandWindows;
    this.port = port;
    this.welcomePage = welcomePage;
    this.logDir = logDir;
    this.config = config;
    
    this.downloadedPackage = Paths.get("nest", String.format("%s-%s.%s", name, version, fileType));
    this.extractDir = new File("zoo", name + "-" + version);

  }

  /**
   * Will be used for naming the download file and extracted folder.
   */
  public String getName() {
    return name;
  }

  /**
   * Will be used for naming the download file and extracted folder.
   */
  public String getVersion() {
    return version;
  }

  /**
   * URL from which the runtime can be downloaded.
   */
  public String getDownloadUrl() {
    return downloadUrl;
  }
  
  /**
   * Type of the download file (usually 'zip').
   */
  public String getFileType() {
    return fileType;
  }

  /**
   * Directory for auto-deployments.
   */
  public String getDeploymentDir() {
    return deploymentDir;
  }

  /**
   * Command that starts the runtime in the background (matching the current OS).
   */
  public String getStartCommand() {
    return Ossy.isWindows() ? startCommandWindows : startCommandLinux;
  }

  /**
   * Command that stops the runtime (after it has been started in the background) (matching the current OS).
   */
  public String getStopCommand() {
    return Ossy.isWindows() ? stopCommandWindows : stopCommandLinux;
  }

  /**
   * Command that starts the runtime in the foreground (matching the current OS).
   */
  public String getStartForegroundCommand() {
    return Ossy.isWindows() ? startForegroundCommandWindows : startForegroundCommandLinux;
  }

  /**
   * Port under which deployed applications are available.
   */
  public String getPort() {
    return port;
  }

  /**
   * URL to the welcome page.
   */
  public String getWelcomePage() {
    return welcomePage;
  }

  /**
   * Directory containing log files.
   */
  public String getLogDir() {
    return logDir;
  }

  /**
   * Configuration file or directory.
   */
  public String getConfig() {
    return config;
  }

  /**
   * Compressed download file.
   */
  public Path getDownloadedPackage() {
    return downloadedPackage;
  }

  /**
   * Direction containing the extracted runtime.
   */
  public File getExtractDir() {
    return extractDir;
  }

  @Override
  public String toString() {
    return this.name;
  }

}
