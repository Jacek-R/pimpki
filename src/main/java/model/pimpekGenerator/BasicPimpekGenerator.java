package model.pimpekGenerator;

import model.configuration.Configuration;
import model.pimpek.Pimpek;

import java.util.Set;

public class BasicPimpekGenerator implements PimpekGenerator {

    private final Configuration configuration;

    public BasicPimpekGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Set<Pimpek> generate() {
        return null;
    }

    @Override
    public Pimpek clone(Pimpek pimpek) {
        return null;
    }
}
