package pl.ds.bulma.components.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.Getter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TagComponent {

  @Inject
  @Getter
  @Default(values = "Label")
  private String label;

  @Inject
  @Getter
  @Default(values = "is-primary")
  private String variant;

  @Inject
  @Getter
  @Default(values = "is-normal")
  private String size;

  @Inject
  private boolean isLight;

  @Inject
  private boolean isRounded;

  @Inject
  @Getter
  private String[] tagClasses;

  @PostConstruct
  private void init() {
    List<String> classes = new ArrayList<>();
    classes.add(size);
    classes.add(variant);
    if (isLight) {
      classes.add("is-light");
    }
    if (isRounded) {
      classes.add("is-rounded");
    }
    tagClasses = classes.toArray(new String[]{});
  }
}
