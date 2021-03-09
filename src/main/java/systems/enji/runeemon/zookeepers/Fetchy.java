package systems.enji.runeemon.zookeepers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;

import systems.enji.runeemon.data.AppException;
import systems.enji.runeemon.data.CommandData;
import systems.enji.runeemon.data.RuntimeData;

/**
 * Downloads a runtime into the downloads folder.
 */
public class Fetchy {

  public static void run(CommandData cd, RuntimeData runtime) throws IOException {

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
    byte[] zip;
    try {
      zip = client.send(request, BodyHandlers.ofByteArray()).body();
    }
    catch (IOException | InterruptedException e) {
      throw new AppException(String.format("could not download %s: %s", runtime.getName(), e.getClass()), e);
    }

    Files.write(runtime.getDownloadedPackage(), zip);

  }

}
