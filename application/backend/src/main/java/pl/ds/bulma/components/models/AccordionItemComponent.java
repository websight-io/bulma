/*
 * Copyright (C) 2022 Dynamic Solutions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.ds.bulma.components.models;

import static org.apache.sling.models.annotations.DefaultInjectionStrategy.OPTIONAL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import pl.ds.bulma.components.services.ComponentIdService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = OPTIONAL)
public class AccordionItemComponent {

  public static final String ID_PREFIX = "accordion_item";

  @OSGiService
  private ComponentIdService idService;

  @Inject
  @Getter
  @Default(values = "Accordion header")
  private String header;

  @Inject
  @Getter
  private String id;

  @PostConstruct
  public void init() {
    id = idService.getTemporaryId(ID_PREFIX);
  }
}
