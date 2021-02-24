package systems.enji.runeemon;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Downloads a runtime into the downloads folder.
 */
class Fetchy {

  /**
   * Downloads the given runtime and returns its path.
   */
  static Path run(CommandData cd, RuntimeData runtimeData) throws IOException, InterruptedException {

    // check if I have to do anything
    if (!cd.getFetch()) {
      return null;
    }

    // compute path to download
    String downloadUrl = runtimeData.getDownloadUrl();
    String fileName = runtimeData.getName() + "-" + runtimeData.getVersion() + ".zip";
    Path downloadedPackage = Paths.get("runtimes/downloaded", fileName);
    
    if (Files.exists(downloadedPackage)) {
      System.out.printf("download already exists: %s\n", fileName);
      return downloadedPackage;
    }

    System.out.printf("downloading %s...\n", fileName);
    HttpClient client = HttpClient.newBuilder().followRedirects(Redirect.NORMAL).build();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(downloadUrl)).build();
    byte[] zip = client.send(request, BodyHandlers.ofByteArray()).body();

    Files.write(downloadedPackage, zip);

    System.out.println("finished downloading");

    return downloadedPackage;

  }

}
