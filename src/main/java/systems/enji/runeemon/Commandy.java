package systems.enji.runeemon;

import java.util.Arrays;
import java.util.List;

/**
 * Translates command line arguments to Runeemon commands.
 */
class Commandy {

  static CommandData run(String[] args) {

    if (args.length == 0) {
      return new CommandData().setRuntimeNames(List.of(CommandData.ALL_RUNTIMES)).setFetch(true).setExtract(true).setInfo(true);
    }
    
    if (args.length == 1) {
      if ("list".equals(args[0])) {
        return new CommandData().setList(true).setRuntimeNames(List.of(CommandData.ALL_RUNTIMES));
      }
      else {
        return new CommandData().setHelp(true);
      }
    }

    CommandData cd = new CommandData();
    for (int i = 0; i < args.length - 1; i++) {
      switch (args[i]) {
        case "fetch":
          cd.setFetch(true);
          break;
        case "extract":
          cd.setFetch(true);
          cd.setExtract(true);
          break;
        case "clean-extracts":
          cd.setCleanExtracts(true);
          break;
        case "clean-downloads":
          cd.setCleanDownloads(true);
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
    // last parameter == runtime list
    return cd.setRuntimeNames(Arrays.asList(args[args.length - 1].split(",")));
    
  }
  
}
