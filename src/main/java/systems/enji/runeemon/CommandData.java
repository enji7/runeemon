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
  private boolean extract = false;
  private boolean cleanDownloads = false;
  private boolean cleanExtracts = false;
  private boolean cleanDeployments = false;
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

  CommandData setExtract(boolean extract) {
    this.extract = extract;
    return this;
  }

  boolean getExtract() {
    return extract;
  }

  CommandData setCleanDownloads(boolean cleanDownloads) {
    this.cleanDownloads = cleanDownloads;
    return this;
  }

  boolean getCleanDownloads() {
    return cleanDownloads;
  }

  CommandData setCleanExtracts(boolean cleanExtracts) {
    this.cleanExtracts = cleanExtracts;
    return this;
  }

  boolean getCleanExtracts() {
    return cleanExtracts;
  }

  CommandData setCleanDeployments(boolean cleanDeployments) {
    this.cleanDeployments = cleanDeployments;
    return this;
  }

  boolean getCleanDeployments() {
    return cleanDeployments;
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
