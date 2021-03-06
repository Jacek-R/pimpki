package pimpek.pimpekGenerator;

import configuration.Configuration;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekModel.PimpekGenre;
import pimpek.pimpekFactory.PimpekFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BasicPimpekGenerator implements PimpekGenerator {

    private final Configuration configuration;
    private final PimpekFactory factory;

    public BasicPimpekGenerator(Configuration configuration, PimpekFactory factory) {
        this.configuration = configuration;
        this.factory = factory;
    }

    @Override
    public Set<Pimpek> generate() {

        Set<Pimpek> beings = new HashSet<>();

        for (Map.Entry<PimpekGenre,Integer> entry : configuration.getPimpeksQuantity().entrySet() ) {
            for (int i=0; i<entry.getValue(); i++) {
                beings.add(factory.create(entry.getKey()));
            }
        }
        return beings;
    }
}
