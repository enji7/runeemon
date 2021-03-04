package systems.enji.runeemon.zookeepers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import systems.enji.runeemon.AppException;
import systems.enji.runeemon.CommandData;
import systems.enji.runeemon.RuntimeData;

public class Cleany {

  public static void run(CommandData cd, RuntimeData runtime) {
    
    if (cd.getCleanZoo()) {
      deleteExtraction(runtime);
    }
    
    if (cd.getCleanNest()) {
      deleteDownload(runtime);
    }
    
    if (cd.getCleanDeployments()) {
      deleteDeployments(runtime);
    }
    
  }

  private static void deleteExtraction(RuntimeData runtime) {
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

  private static void deleteDownload(RuntimeData runtime) {
    if (Files.exists(runtime.getDownloadedPackage())) {
      try {
        Files.delete(runtime.getDownloadedPackage());
      } catch (IOException e) {
        throw new AppException("Could not delete file: " + runtime.getDownloadedPackage(), e);
      }
    }
    System.out.println("deleted download file for " + runtime.getName());
  }

  private static void deleteDeployments(RuntimeData runtime) {
    File deploymentDir = new File(runtime.getExtractDir(), runtime.getDeploymentDir());
    if (deploymentDir.exists()) {
      try {
        Files.list(deploymentDir.toPath())
          .filter(p -> p.getFileName().toString().endsWith(".war"))
          .forEach(p -> {
            try {
              Files.delete(p);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          });
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
  
}
