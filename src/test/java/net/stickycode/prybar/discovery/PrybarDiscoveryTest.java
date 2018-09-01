package net.stickycode.prybar.discovery;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

public class PrybarDiscoveryTest {

  @Test
  public void blah() {

    Application app = new Application();
    app.scan("net.stickycode");
    
   
    assertThat(app.yaml()).isEqualTo("");
  }
}
