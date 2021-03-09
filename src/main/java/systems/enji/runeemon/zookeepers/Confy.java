package systems.enji.runeemon.zookeepers;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import systems.enji.runeemon.data.AppException;
import systems.enji.runeemon.data.CommandData;
import systems.enji.runeemon.data.RuntimeData;

/**
 * Provides runtime configuration data.
 */
public class Confy {

  /**
   * Returns a list with meta data for the requested runtimes; 
   */
  public static List<RuntimeData> run(CommandData cd) throws IOException {

    List<RuntimeData> runtimes = new LinkedList<>();

    // runtime names given on the command line
    Set<String> requestedConfigNames = cd.getRuntimeNames().stream()
        // we don't care about case
        .map(String::toLowerCase)
        // this will make filtering easier in the next step
        .map(s -> s + ".properties")
        .collect(Collectors.toSet());
    
    // available configs, keyed with property file name
    Map<String, Path> configs = Files.list(Paths.get("config/runtimes"))
        // don't load the template
        .filter(p -> !"template.properties".equals(p.getFileName().toString()))
        .collect(Collectors.toMap(p -> p.getFileName().toString(), p -> p));
    
    // transform "all" to concrete names
    if (requestedConfigNames.contains(CommandData.ALL_RUNTIMES + ".properties")) {
      requestedConfigNames = configs.keySet();
    }
    
    for (String requestedConfig : requestedConfigNames) {
      
      Path configPath = configs.get(requestedConfig);
      
      if (configPath == null) {
        throw new AppException(String.format("There is no configured runtime with the name '%s'.",  requestedConfig));
      }
      
      Properties props = new Properties();
      props.load(new FileReader(configPath.toFile()));
      
      // peel off ".properties"
      String configName = configPath.getFileName().toString();
      String name = configName.substring(0, configName.indexOf(".properties"));
      
      runtimes.add(new RuntimeData(name, props.getProperty("version"), props.getProperty("downloadUrl"),
          props.getProperty("fileType"), props.getProperty("deploymentDir"), props.getProperty("startCommand"),
          props.getProperty("stopCommand"), props.getProperty("startForegroundCommand"), props.getProperty("port"),
          props.getProperty("welcomePage"), props.getProperty("logDir"), props.getProperty("config")));      

    }
    
    return runtimes;
    
  }
  
}
