package systems.enji.runeemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Unzips a previously downloaded runtime into the extracted folder.
 * Who would have thought that this is still so cumbersome in 2021?
 * Kudos to https://www.baeldung.com/java-compress-and-uncompress for providing the basics.
 * 
 * TODO: change directory structure:
 * - downloads
 * - runtimes (i.e., no "extracted" subdirectory)
 */
class Zippy {

  /**
   * Unzips the given downloaded package, using meta data for the given runtime.
   */
  static void run(CommandData cd, RuntimeData runtime) throws IOException {
    
    // check if I have to do anything
    if (!cd.getExtract()) {
      return;
    }

    // prepare destination directory
    File extractDir = runtime.getExtractDir();
    if (extractDir.exists()) {
      System.out.printf("extracted: %s\n", extractDir.getAbsolutePath());
      return;
    }
    System.out.printf("extracting to %s...\n", extractDir.getAbsolutePath());
    
    // all runtimes are zipped with a root directory, which we will later rename
    extractDir = extractDir.getParentFile();
    
    String extractedRootName = null;
    
    try (ZipInputStream zis = new ZipInputStream(new FileInputStream(runtime.getDownloadedPackage().toFile()))) {

      byte[] buffer = new byte[4096];
      
      ZipEntry zipEntry;
      while ((zipEntry = zis.getNextEntry()) != null) {

        File zipEntryFile = zipEntryFile(extractDir, zipEntry);
        if (zipEntry.isDirectory()) {
          
          // create directory
          
          if (extractedRootName == null) {
            extractedRootName = zipEntryFile.getName();
          }
          
          if (!zipEntryFile.isDirectory() && !zipEntryFile.mkdirs()) {
            throw new RuntimeException("Could not create directory: " + zipEntryFile);
          }
          
        } else {
          
          // we need to explicitly create the parent directory in case of archives created under Windows
          File parent = zipEntryFile.getParentFile();
          if (!parent.isDirectory() && !parent.mkdirs()) {
            throw new RuntimeException("Failed to create directory " + parent);
          }

          writeFile(zis, zipEntryFile, buffer);
          
        }

        zis.closeEntry();
        
      }
    }

    // rename the root directory
    File extractedRootDir = new File(extractDir, extractedRootName);
    if (!runtime.getExtractDir().equals(extractedRootDir)) {
      Files.move(extractedRootDir.toPath(), runtime.getExtractDir().toPath());
    }
    
    makeExecutable(runtime.getExtractDir(), runtime);
    
  }

  /**
   * Resolves a destination file for the given ZIP entry, performing a security check along the way.
   */
  private static File zipEntryFile(File destinationDir, ZipEntry zipEntry) throws IOException {
    
    File zipEntryFile = new File(destinationDir, zipEntry.getName());

    // security check: must not escape the destination directory
    if (!zipEntryFile.getCanonicalPath().startsWith(destinationDir.getCanonicalPath() + File.separator)) {
      throw new RuntimeException("ZIP entry trying to escape: " + zipEntry.getName());
    }

    return zipEntryFile;
    
  }

  /**
   * Writes the content of the given ZIP input stream to the given file, using the provided buffer.
   */
  private static void writeFile(ZipInputStream zis, File zipEntryFile, byte[] buffer)
      throws IOException, FileNotFoundException {
    try (FileOutputStream fos = new FileOutputStream(zipEntryFile)) {
      int len;
      while ((len = zis.read(buffer)) > 0) {
        fos.write(buffer, 0, len);
      }
    }
  }

  /**
   * Makes the start and stop commands of the given runtime executable.
   */
  private static void makeExecutable(File extractedRootDir, RuntimeData runtime) {
    makeExecutable(extractedRootDir, runtime.getStartForegroundCommand());
    makeExecutable(extractedRootDir, runtime.getStartCommand());
    makeExecutable(extractedRootDir, runtime.getStopCommand());
  }

  /**
   * Makes the given command (shell script) executable.
   */
  private static void makeExecutable(File extractedRootDir, String command) {
    
    // certain commands are configured with parameters, which have to be stripped off at this point
    int spaceIndex = command.indexOf(" ");
    if (spaceIndex != -1) {
      command = command.substring(0, spaceIndex);
    }
    
    if (command != null && !command.isBlank()) {
      File commandFile = new File(extractedRootDir, command);
      commandFile.setExecutable(true);
    }
    
  }

}
