package systems.enji.runeemon.zookeepers;

import java.util.LinkedList;
import java.util.List;

import systems.enji.runeemon.data.AppException;
import systems.enji.runeemon.data.CommandData;

/**
 * Translates command line arguments to Runeemon commands.
 */
public class Commandy {

  public static CommandData run(String[] args) {

    // no args => print help
    if (args.length == 0) {
      return new CommandData().setHelp(true);
    }
    
    // one arg => either a "list", or a cry for help
    if (args.length == 1) {
      if ("list".equals(args[0])) {
        return new CommandData().setList(true).setRuntimeNames(List.of(CommandData.ALL_RUNTIMES));
      }
      else {
        return new CommandData().setHelp(true);
      }
    }

    CommandData cd = assembleCommands(args);
    
    validate(cd);
    
    return cd;
    
  }

  private static CommandData assembleCommands(String[] args) {
    CommandData cd = new CommandData();
    List<String> runtimeNames = new LinkedList<>();
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "fetch":
          cd.setFetch(true);
          break;
        case "hatch":
          cd.setFetch(true);
          cd.setHatch(true);
          break;
        case "deploy":
          cd.setFetch(true);
          cd.setHatch(true);
          cd.setDeploy(true);
          break;
        case "start":
          cd.setFetch(true);
          cd.setHatch(true);
          cd.setDeploy(true);
          cd.setStart(true);
          break;
        case "start-bg":
          cd.setFetch(true);
          cd.setHatch(true);
          cd.setDeploy(true);
          cd.setStartBackground(true);
          break;
        case "start-fg":
          cd.setFetch(true);
          cd.setHatch(true);
          cd.setDeploy(true);
          cd.setStartForeground(true);
          break;
        case "stop":
          cd.setStop(true);
          break;
        case "clean-zoo":
          cd.setCleanZoo(true);
          break;
        case "clean-nest":
          cd.setCleanNest(true);
          break;
        case "clean-deployments":
          cd.setCleanDeployments(true);
          break;
        case "info":
          cd.setInfo(true);
          break;
        default:
          // assume that this is a runtime name for now
          runtimeNames.add(args[i]);
          break;
      }
    }
    cd.setRuntimeNames(runtimeNames);
    return cd;
  }

  private static void validate(CommandData cd) {
    if (cd.getRuntimeNames().size() > 1) {
      if (cd.getStart() || cd.getStartBackground() || cd.getStartForeground()) {
        throw new AppException("cannot start more than one runtime at once");
      }
    }
  }

}
