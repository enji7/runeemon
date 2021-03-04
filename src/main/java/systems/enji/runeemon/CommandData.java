package systems.enji.runeemon;

import java.util.LinkedList;
import java.util.List;

/**
 * Data that is passed from one command to the other.
 */
public class CommandData {

  public static final String ALL_RUNTIMES = "all";
  
  private boolean help = false;
  private boolean list = false;
  private List<String> runtimeNames = new LinkedList<>();
  private boolean fetch = false;
  private boolean hatch = false;
  private boolean deploy = false;
  private boolean cleanNest = false;
  private boolean cleanZoo = false;
  private boolean cleanDeployments = false;
  private boolean start = false;
  private boolean startForeground = false;
  private boolean startBackground = false;
  private boolean stop = false;
  private boolean info = false;
  
  public CommandData setHelp(boolean help) {
    this.help = help;
    return this;
  }
  
  public boolean getHelp() {
    return help;
  }

  public CommandData setList(boolean list) {
    this.list = list;
    return this;
  }

  public boolean getList() {
    return list;
  }

  public CommandData setRuntimeNames(List<String> runtimeNames) {
    this.runtimeNames = runtimeNames;
    return this;
  }

  public List<String> getRuntimeNames() {
    return runtimeNames;
  }

  public CommandData setFetch(boolean fetch) {
    this.fetch = fetch;
    return this;
  }

  public boolean getFetch() {
    return fetch;
  }

  public CommandData setHatch(boolean hatch) {
    this.hatch = hatch;
    return this;
  }

  public boolean getHatch() {
    return hatch;
  }

  public CommandData setDeploy(boolean deploy) {
    this.deploy = deploy;
    return this;
  }

  public boolean getDeploy() {
    return deploy;
  }

  public CommandData setCleanNest(boolean cleanNest) {
    this.cleanNest = cleanNest;
    return this;
  }

  public boolean getCleanNest() {
    return cleanNest;
  }

  public CommandData setCleanZoo(boolean cleanZoo) {
    this.cleanZoo = cleanZoo;
    return this;
  }

  public boolean getCleanZoo() {
    return cleanZoo;
  }

  public CommandData setCleanDeployments(boolean cleanDeployments) {
    this.cleanDeployments = cleanDeployments;
    return this;
  }

  public boolean getCleanDeployments() {
    return cleanDeployments;
  }

  public CommandData setStart(boolean start) {
    this.start = start;
    return this;
  }

  public boolean getStart() {
    return start;
  }

  public CommandData setStartForeground(boolean startForeground) {
    this.startForeground = startForeground;
    return this;
  }
  
  public boolean getStartForeground() {
    return startForeground;
  }
  
  public CommandData setStartBackground(boolean startBackground) {
    this.startBackground = startBackground;
    return this;
  }

  public boolean getStartBackground() {
    return startBackground;
  }

  public CommandData setStop(boolean stop) {
    this.stop = stop;
    return this;
  }

  public boolean getStop() {
    return stop;
  }

  public CommandData setInfo(boolean info) {
    this.info = info;
    return this;
  }

  public boolean getInfo() {
    return info;
  }

}
