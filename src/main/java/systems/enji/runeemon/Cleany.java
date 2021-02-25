package systems.enji.runeemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

class Cleany {

  static void run(CommandData cd, RuntimeData runtime) {
    
    if (cd.getCleanExtracts()) {
      deleteDirectory(runtime);
    }
    
    if (cd.getCleanDownloads()) {
      deleteFile(runtime);
    }
    
    if (cd.getCleanDeployments()) {
      // TODO: implement
    }
    
  }

  private static void deleteDirectory(RuntimeData runtime) {
    if (runtime.getExtractDir().exists()) {
      try {
        Files.walk(runtime.getExtractDir().toPath())
        .sorted(Comparator.reverseOrder())
        .map(Path::toFile)
        .forEach(File::delete);
      } catch (IOException e) {
        throw new AppException("Could not delete directory: " + runtime.getExtractDir(), e);
      }
    }
    System.out.println("deleted uncompressed files for " + runtime.getName());
  }

  private static void deleteFile(RuntimeData runtime) {
    if (Files.exists(runtime.getDownloadedPackage())) {
      try {
        Files.delete(runtime.getDownloadedPackage());
      } catch (IOException e) {
        throw new AppException("Could not delete file: " + runtime.getDownloadedPackage(), e);
      }
    }
    System.out.println("deleted download file for " + runtime.getName());
  }

}
