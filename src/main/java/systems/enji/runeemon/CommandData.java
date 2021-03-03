package systems.enji.runeemon;

import java.util.LinkedList;
import java.util.List;

/**
 * Data that is passed from one command to the other.
 */
class CommandData {

  static final String ALL_RUNTIMES = "all";
  
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
  
  CommandData setHelp(boolean help) {
    this.help = help;
    return this;
  }
  
  boolean getHelp() {
    return help;
  }

  boolean getList() {
    return list;
  }

  CommandData setList(boolean list) {
    this.list = list;
    return this;
  }

  CommandData setRuntimeNames(List<String> runtimeNames) {
    this.runtimeNames = runtimeNames;
    return this;
  }

  List<String> getRuntimeNames() {
    return runtimeNames;
  }

  CommandData setFetch(boolean fetch) {
    this.fetch = fetch;
    return this;
  }

  boolean getFetch() {
    return fetch;
  }

  CommandData setHatch(boolean hatch) {
    this.hatch = hatch;
    return this;
  }

  boolean getHatch() {
    return hatch;
  }

  public CommandData setDeploy(boolean deploy) {
    this.deploy = deploy;
    return this;
  }

  public boolean getDeploy() {
    return deploy;
  }

  CommandData setCleanNest(boolean cleanNest) {
    this.cleanNest = cleanNest;
    return this;
  }

  boolean getCleanNest() {
    return cleanNest;
  }

  CommandData setCleanZoo(boolean cleanZoo) {
    this.cleanZoo = cleanZoo;
    return this;
  }

  boolean getCleanZoo() {
    return cleanZoo;
  }

  CommandData setCleanDeployments(boolean cleanDeployments) {
    this.cleanDeployments = cleanDeployments;
    return this;
  }

  boolean getCleanDeployments() {
    return cleanDeployments;
  }

  CommandData setStart(boolean start) {
    this.start = start;
    return this;
  }

  boolean getStart() {
    return start;
  }

  CommandData setStartForeground(boolean startForeground) {
    this.startForeground = startForeground;
    return this;
  }
  
  boolean getStartForeground() {
    return startForeground;
  }
  
  CommandData setStartBackground(boolean startBackground) {
    this.startBackground = startBackground;
    return this;
  }

  boolean getStartBackground() {
    return startBackground;
  }

  CommandData setStop(boolean stop) {
    this.stop = stop;
    return this;
  }

  boolean getStop() {
    return stop;
  }

  CommandData setInfo(boolean info) {
    this.info = info;
    return this;
  }

  boolean getInfo() {
    return info;
  }

}
