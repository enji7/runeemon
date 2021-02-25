package systems.enji.runeemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandyTest {

  @Test
  public void empty() {
    CommandData cd = Commandy.run(new String[0]);
    assertEquals(List.of(CommandData.ALL_RUNTIMES), cd.getRuntimeNames());
  }
  
  @Test
  public void help() {
    CommandData cd = Commandy.run(new String[]{ "help" });
    assertTrue(cd.getHelp());
  }
  
  @Test
  public void helpIrregular() {
    CommandData cd = Commandy.run(new String[]{ "xyz" });
    assertTrue(cd.getHelp());
  }

  @Test
  public void list() {
    CommandData cd = Commandy.run(new String[]{ "list" });
    assertTrue(cd.getList());
  }
  
  @Test
  public void oneRuntime() {
    CommandData cd = Commandy.run(new String[]{ "fetch", "wildfly" });
    assertEquals(List.of("wildfly"), cd.getRuntimeNames());
  }
  
  @Test
  public void twoRuntimes() {
    CommandData cd = Commandy.run(new String[]{ "fetch", "wildfly,openliberty" });
    assertEquals(List.of("wildfly", "openliberty"), cd.getRuntimeNames());
  }
  
}
