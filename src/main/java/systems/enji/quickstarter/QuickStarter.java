package systems.enji.quickstarter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * TODO:
 * - default operation:
 *   - for all configured runtimes (or those given on command line)... 
 *   - if already extracted: next runtime
 *   - if already downloaded: extract
 *   - else: download and extract
 * - clean extractions
 *   - for all configured runtimes (or those given on command line)...
 *   - rm extraction (if present)
 *   - download (if not available)
 *   - extract again
 * - clean download
 *   - for all configured runtimes (or those given on command line)...
 *   - rm download and extraction (if present)
 *   - download and extract again
 *
 */
public class QuickStarter {

  public static void main(String[] args) throws Exception {

    String downloadUrl = "https://download.jboss.org/wildfly/22.0.1.Final/wildfly-22.0.1.Final.zip";
    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1);
    String name = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1, downloadUrl.indexOf(".zip"));

    Path downloadedPackage = download(downloadUrl, fileName);
    unzip(downloadedPackage, name);

  }

  private static Path download(String downloadUrl, String fileName) throws IOException, InterruptedException {

    Path downloadedPackage = Paths.get("runtimes/downloaded", fileName);
    if (Files.exists(downloadedPackage)) {
      System.out.printf("download already exists: %s\n", fileName);
      return downloadedPackage;
    }

    System.out.printf("downloading %s...\n", fileName);
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(downloadUrl)).build();
    byte[] zip = client.send(request, BodyHandlers.ofByteArray()).body();

    Files.write(downloadedPackage, zip);

    System.out.println("finished downloading");

    return downloadedPackage;

  }

  private static void unzip(Path downloadedPackage, String name) throws IOException {
    
    File destDir = new File("runtimes/extracted", name);
    if (destDir.exists()) {
      System.out.printf("already extracted: %s\n", destDir);
      return;
    }
    destDir.mkdir();

    System.out.printf("extracting to %s...\n", destDir);
    
    byte[] buffer = new byte[1024];
    ZipInputStream zis = new ZipInputStream(new FileInputStream(downloadedPackage.toFile()));
    ZipEntry zipEntry = zis.getNextEntry();
    while (zipEntry != null) {

      File newFile = newFile(destDir, zipEntry);
      if (zipEntry.isDirectory()) {
        if (!newFile.isDirectory() && !newFile.mkdirs()) {
          throw new IOException("Failed to create directory " + newFile);
        }
      } else {
        // fix for Windows-created archives
        File parent = newFile.getParentFile();
        if (!parent.isDirectory() && !parent.mkdirs()) {
          throw new IOException("Failed to create directory " + parent);
        }

        // write file content
        FileOutputStream fos = new FileOutputStream(newFile);
        int len;
        while ((len = zis.read(buffer)) > 0) {
          fos.write(buffer, 0, len);
        }
        fos.close();
      }
      zipEntry = zis.getNextEntry();

    }
    zis.closeEntry();
    zis.close();

    // TODO: prevent double dir problem, emulate "extract here";
    // how to: if the zip contains exactly one root element, which is a dir: use this as target dir
    
    System.out.println("extraction complete");
    
  }
  
  
  private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
    File destFile = new File(destinationDir, zipEntry.getName());

    String destDirPath = destinationDir.getCanonicalPath();
    String destFilePath = destFile.getCanonicalPath();

    if (!destFilePath.startsWith(destDirPath + File.separator)) {
      throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
    }

    return destFile;
  }

}
