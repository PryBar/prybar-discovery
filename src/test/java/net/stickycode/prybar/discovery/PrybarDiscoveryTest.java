package net.stickycode.prybar.discovery;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class PrybarDiscoveryTest {

  @Rule
  public TestName name = new TestName();

  @Test
  public void blah() {

    PrybarDiscovery app = new PrybarDiscovery();
    app.scan("net.stickycode");

    assertThat(app.yaml()).isEqualTo("");
  }

  @Test
  public void empty() {
    PrybarDiscovery discovery = new PrybarDiscovery();
    discovery.scan("net.stickycode.discovery.examples.empty");
    assertThat(discovery.yaml()).isEqualTo(example());

  }

  public String example() {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(getPath()))) {
      return buffer.lines().collect(Collectors.joining("\n"));
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private InputStream getPath() {
    return getClass().getResourceAsStream("examples/" + name.getMethodName() + "/prybar.yaml");
  }
}
