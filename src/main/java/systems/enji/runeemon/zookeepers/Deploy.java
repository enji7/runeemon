package systems.enji.runeemon.zookeepers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import systems.enji.runeemon.CommandData;
import systems.enji.runeemon.RuntimeData;

public class Deploy {

  /**
   * Copies the WARs from Runeemon's autodeploy directory to the given runtime's autodeploy directory.
   */
  public static void run(CommandData cd, RuntimeData runtime) {
    if (!cd.getDeploy()) {
      return;
    }
    try {
      Files.list(Paths.get("autodeploy")).filter(p -> p.getFileName().toString().endsWith(".war")).forEach(p -> {
        try {
          Path deploymentDir = Paths.get(runtime.getExtractDir().toString(), runtime.getDeploymentDir());
          deploymentDir.toFile().mkdirs();
          Path targetFile = Paths.get(deploymentDir.toFile().getAbsolutePath(), p.getFileName().toString());
          Files.copy(p, targetFile, StandardCopyOption.REPLACE_EXISTING);
          System.out.println("deployed: " + targetFile.toFile().getAbsolutePath());
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
}
