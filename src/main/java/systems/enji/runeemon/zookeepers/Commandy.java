package systems.enji.runeemon.zookeepers;

import java.util.Arrays;
import java.util.List;

import systems.enji.runeemon.data.AppException;
import systems.enji.runeemon.data.CommandData;

/**
 * Translates command line arguments to Runeemon commands.
 */
public class Commandy {

  public static CommandData run(String[] args) {

    if (args.length == 0) {
      return new CommandData().setHelp(true);
    }
    
    if (args.length == 1) {
      if ("list".equals(args[0])) {
        return new CommandData().setList(true).setRuntimeNames(List.of(CommandData.ALL_RUNTIMES));
      }
      else {
        return new CommandData().setHelp(true);
      }
    }

    CommandData cd = assembleCommands(args);
    
    // last parameter == runtime list
    cd.setRuntimeNames(Arrays.asList(args[args.length - 1].split(",")));
    
    validate(cd);
    
    return cd;
    
  }

  private static CommandData assembleCommands(String[] args) {
    CommandData cd = new CommandData();
    for (int i = 0; i < args.length - 1; i++) {
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
          throw new AppException(String.format("unknown parameter: '%s'\n", args[i]));
      }
    }
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
