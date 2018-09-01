package net.stickycode.prybar.discovery;

import java.util.List;

public class PrybarComponent {

  private Class<?> type;
  private List<Wiring> wiring;

  public PrybarComponent(String type) {
    try {
      this.type = Class.forName(type);
    }
    catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public String getType() {
    return type.getName();
  }

}
