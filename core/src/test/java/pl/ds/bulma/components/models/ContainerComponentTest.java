package pl.ds.bulma.components.models;

import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit5.SlingContext;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SlingContextExtension.class)
class ContainerComponentTest {

    private static final String PATH = "/content/container";
    private final SlingContext context = new SlingContext(ResourceResolverType.RESOURCERESOLVER_MOCK);

    @BeforeEach
    public void init() {
        context.addModelsForClasses(ContainerComponent.class);
        context.load().json(requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("container.json")), PATH);
    }

    @Test
    void defaultContainerComponentModelTest() {
        ContainerComponent model = context.resourceResolver().getResource(PATH + "/default").adaptTo(ContainerComponent.class);

        assertThat(model).isNotNull();
        assertThat(model.getContainerStyle()).isEmpty();
    }

    @Test
    void containerComponentModelTest() {
        ContainerComponent model = context.resourceResolver().getResource(PATH + "/complex").adaptTo(ContainerComponent.class);

        assertThat(model).isNotNull();
        assertThat(model.getContainerStyle()).isEqualTo("is-fullhd");
    }
}
