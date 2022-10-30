package xyz.mlserver.util.reflections;

import org.reflections.util.ConfigurationBuilder;
import xyz.mlserver.util.Chain;

public class ReflectionsConfigurationBuilder extends ConfigurationBuilder implements Chain<ReflectionsConfigurationBuilder> {
    public ReflectionsConfigurationBuilder() {
        super();
    }
}
