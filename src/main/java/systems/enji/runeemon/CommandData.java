package systems.enji.runeemon;

/**
 * Data that is passed from one command to the other.
 */
class CommandData {

  String runtimeName = "all";
  boolean fetch = true;
  boolean extract = true;
  boolean clean = false;
  boolean purge = false;
  boolean startForeground = false;
  boolean startBackground = false;
  boolean stop = false;
  boolean info = true;
  
}
