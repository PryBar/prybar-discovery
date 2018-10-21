package net.stickycode.prybar.discovery;

import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import net.stickycode.stereotype.StickyComponent;

public class Application {

  private List<PrybarComponentDefinition> components = new ArrayList<>();

  public List<PrybarComponentDefinition> getComponents() {
    return components;
  }

  public void setComponents(List<PrybarComponentDefinition> components) {
    this.components = components;
  }

  public void scan(String... packages) {
    ScanResult scanner = new FastClasspathScanner(packages).scan();
    List<String> componentAnnotations = scanner.getNamesOfAnnotationsWithMetaAnnotation(StickyComponent.class.getName());
    componentAnnotations.add(StickyComponent.class.getName());
    List<String> componentsTypes = scanner
      .getNamesOfClassesWithAnnotationsAnyOf(componentAnnotations.toArray(new String[componentAnnotations.size()]));
    for (String type : componentsTypes) {
      components.add(new PrybarComponentDefinition(type));
    }
  }

  public String yaml() {
    DumperOptions options = new DumperOptions();
    options.setAllowReadOnlyProperties(true);
    options.setDefaultFlowStyle(FlowStyle.BLOCK);
    // options.setPrettyFlow(true);
    Yaml yaml = new Yaml(options);
    return yaml.dump(this);
  }

}
