package systems.enji.runeemon;

/**
 * Prints usage info.
 */
class Helpey {

  static void run(CommandData cd) {
    if (!cd.getHelp()) {
      return;
    }
    System.out.println("Usage: TODO");
  }
  
}
