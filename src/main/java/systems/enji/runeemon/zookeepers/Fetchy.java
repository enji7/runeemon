package systems.enji.runeemon.zookeepers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;

import systems.enji.runeemon.CommandData;
import systems.enji.runeemon.RuntimeData;

/**
 * Downloads a runtime into the downloads folder.
 */
public class Fetchy {

  /**
   * Downloads the given runtime and returns its path.
   */
  public static void run(CommandData cd, RuntimeData runtime) throws IOException, InterruptedException {

    // check if I have to do anything
    if (!cd.getFetch()) {
      return;
    }

    if (Files.exists(runtime.getDownloadedPackage())) {
      System.out.printf("downloaded: %s\n", runtime.getDownloadedPackage().toFile().getAbsolutePath());
      return;
    }

    System.out.printf("downloading to %s...\n", runtime.getDownloadedPackage().toFile().getAbsolutePath());
    HttpClient client = HttpClient.newBuilder().followRedirects(Redirect.NORMAL).build();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(runtime.getDownloadUrl())).build();
    byte[] zip = client.send(request, BodyHandlers.ofByteArray()).body();

    Files.write(runtime.getDownloadedPackage(), zip);

  }

}