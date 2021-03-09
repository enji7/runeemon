package systems.enji.runeemon;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import systems.enji.runeemon.data.AppException;
import systems.enji.runeemon.data.CommandData;
import systems.enji.runeemon.data.RuntimeData;
import systems.enji.runeemon.zookeepers.Confy;

public class ConfyTest {

  @Test
  public void allRuntimes() throws IOException {
    List<RuntimeData> rts = Confy.run(new CommandData().setRuntimeNames(List.of(CommandData.ALL_RUNTIMES)));
    assertTrue(rts.size() > 3);
  }
  
  @Test
  public void oneRuntime() throws IOException {
    List<RuntimeData> rts = Confy.run(new CommandData().setRuntimeNames(List.of("WILDFLY")));
    Assertions.assertEquals(1, rts.size());
    Assertions.assertEquals("wildfly", rts.get(0).getName());
  }
  
  @Test
  public void twoRuntimes() throws IOException {
    List<RuntimeData> rts = Confy.run(new CommandData().setRuntimeNames(List.of("WILDFLY", "PAYARA")));
    Assertions.assertEquals(2, rts.size());
    Assertions.assertEquals("wildfly", rts.get(0).getName());
    Assertions.assertEquals("payara", rts.get(1).getName());
  }

  @Test
  public void wrongRuntime() throws IOException {
    Assertions.assertThrows(AppException.class,
        () -> Confy.run(new CommandData().setRuntimeNames(List.of("WILDFLY", "PAYARA", "FOOBAR"))));
  }
  
  @Test
  public void noRuntimes() throws IOException {
    List<RuntimeData> rts = Confy.run(new CommandData());
    assertTrue(rts.isEmpty());
  }

}
