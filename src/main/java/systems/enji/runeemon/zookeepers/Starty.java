package systems.enji.runeemon.zookeepers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    exec(runtime.getExtractDir(), command);
  }
  
  private static void startBackground(RuntimeData runtime) {
    String command = runtime.getStartCommand();
    if (command == null || command.isBlank()) {
      System.out.printf("can't start %s in the background because it has no explicit background start command\n", runtime.getName());
      return;
    }
    exec(runtime.getExtractDir(), command);
  }
  
  private static void startForeground(RuntimeData runtime) {
    String command = runtime.getStartForegroundCommand();
    if (command == null || command.isBlank()) {
      System.out.printf("can't start %s in the foreground because it has no explicit foreground start command\n", runtime.getName());
      return;
    }
    exec(runtime.getExtractDir(), command);
  }
  
  private static void stop(RuntimeData runtime) {
    String command = runtime.getStopCommand();
    if (command == null || command.isBlank()) {
      System.out.printf("can't stop %s because it has no explicit stop command\n", runtime.getName());
      return;
    }
    exec(runtime.getExtractDir(), command);
  }
  
  private static void exec(File runtimeDir, String command) {
    
    ProcessBuilder pb = new ProcessBuilder(assembleCommand(runtimeDir, command));
    
    // direct entry for TomEE; might move to configuration one day
    pb.environment().put("CATALINA_HOME", runtimeDir.getAbsolutePath());
    
    if (Ossy.isWindows()) {
      pb.redirectOutput(Redirect.INHERIT);
      pb.redirectError(Redirect.INHERIT);
    }

    try {
      
      Process process = pb.start();
      
      if (!Ossy.isWindows()) {
        
        // on Linux, we just have to take care of reading everything that comes out of the started process...
        consumeOutput(process);
        
        // ...and wait until the process has finished
        process.waitFor();
        
      }
      else {
        // on Windows, the Linux solution above might hang forever;
        // instead, we have redirected all output to that of the current process above
        // and now wait a little bit to give the server the opportunity to spawn;
        // drawback of this solution: the original command prompt will pop up after this sleep time, no matter
        // if the invoked operation has finished or not
        Thread.sleep(1000);
      }

    }
    catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  private static List<String> assembleCommand(File runtimeDir, String command) {
    
    List<String> commandList = new LinkedList<>();
    if (Ossy.isWindows()) {
      commandList.add("cmd");
      commandList.add("/c");
    }
    commandList.addAll(Arrays.asList((runtimeDir.getAbsolutePath() + command).split(" ")));
    
    System.out.println("executing: " + commandList.stream().collect(Collectors.joining(" ")));
    
    return commandList;
    
  }

  private static void consumeOutput(Process process) {
    new Thread(() -> {
      new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(System.out::println);
    }).start();
    new Thread(() -> {
      new BufferedReader(new InputStreamReader(process.getErrorStream())).lines().forEach(System.out::println);
    }).start();
  }

}
