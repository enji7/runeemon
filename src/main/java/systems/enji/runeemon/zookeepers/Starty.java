package systems.enji.runeemon.zookeepers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import systems.enji.runeemon.data.CommandData;
import systems.enji.runeemon.data.RuntimeData;

/**
 * Starts a runtime.
 */
public class Starty {

  public static void run(CommandData cd, RuntimeData runtime) {
    if (cd.getStartBackground()) {
      startBackground(runtime);
    }
    else if (cd.getStartForeground()) {
      startForeground(runtime);
    }
    else if (cd.getStart()) {
      start(runtime);
    }
    else if (cd.getStop()) {
      stop(runtime);
    }
  }
  
  private static void start(RuntimeData runtime) {
    String command = runtime.getStartCommand();
    if (command == null || command.isBlank()) {
      command = runtime.getStartForegroundCommand();
    }
    exec(runtime.getExtractDir().getAbsolutePath() + command);
  }
  
  private static void startBackground(RuntimeData runtime) {
    String command = runtime.getStartCommand();
    if (command == null || command.isBlank()) {
      System.out.printf("can't start %s in the background because it has no explicit background start command\n", runtime.getName());
      return;
    }
    exec(runtime.getExtractDir().getAbsolutePath() + command);
  }
  
  private static void startForeground(RuntimeData runtime) {
    String command = runtime.getStartForegroundCommand();
    if (command == null || command.isBlank()) {
      System.out.printf("can't start %s in the foreground because it has no explicit foreground start command\n", runtime.getName());
      return;
    }
    exec(runtime.getExtractDir().getAbsolutePath() + command);
  }
  
  private static void stop(RuntimeData runtime) {
    String command = runtime.getStopCommand();
    if (command == null || command.isBlank()) {
      System.out.printf("can't stop %s because it has no explicit stop command\n", runtime.getName());
      return;
    }
    exec(runtime.getExtractDir().getAbsolutePath() + command);
  }
  
  private static void exec(String command) {
    try {
      Process process = Runtime.getRuntime().exec(command);
      new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(System.out::println);
      int exitCode = process.waitFor();
      System.out.println("exit code: " + exitCode);
      
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  
}
