package systems.enji.runeemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Unzips a previously downloaded runtime into the extracted folder.
 * Who would have thought that this is still so cumbersome in 2021?
 * Kudos to https://www.baeldung.com/java-compress-and-uncompress for providing the basis.
 */
class Zippy {

  /**
   * Unzips the given downloaded package, using meta data for the given runtime.
   */
  static void unzip(Path downloadedPackage, RuntimeData runtimeData) throws IOException {
    
    // prepare destination directory
    File destDir = new File("runtimes/extracted", runtimeData.getName() + "-" + runtimeData.getVersion());
    if (destDir.exists()) {
      System.out.printf("already extracted: %s\n", destDir);
      return;
    }
    destDir.mkdir();

    System.out.printf("extracting to %s...\n", destDir);
    
    
    try (ZipInputStream zis = new ZipInputStream(new FileInputStream(downloadedPackage.toFile()))) {

      byte[] buffer = new byte[4096];
      
      ZipEntry zipEntry;
      while ((zipEntry = zis.getNextEntry()) != null) {

        File zipEntryFile = zipEntryFile(destDir, zipEntry);
        if (zipEntry.isDirectory()) {
          
          // create directory
          
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

    // TODO: prevent double dir problem, emulate "extract here";
    // how to: if the zip contains exactly one root element, which is a dir: use this as target dir
    
    System.out.println("extraction complete");
    
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

}
