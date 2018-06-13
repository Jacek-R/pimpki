package model.pimpekGenerator;

import model.configuration.Configuration;
import model.helpers.NameGenerator;
import model.pimpek.Pimpek;
import model.pimpek.PimpekGenre;
import model.pimpek.Predator;
import model.pimpek.SimplePimpek;

public class BasicPimpekFactory implements PimpekFactory {

    private final NameGenerator nameGenerator;
    private final Configuration configuration;

    public BasicPimpekFactory(NameGenerator nameGenerator, Configuration configuration) {
        this.nameGenerator = nameGenerator;
        this.configuration = configuration;
    }


    @Override
    public Pimpek create(PimpekGenre genre) {

        String name = nameGenerator.generate(genre);
        int energy = configuration.getInitialEnergy();
        int cloningCost = configuration.getCloningCost();
        Pimpek being;
        switch (genre) {
            case PREDATOR:
                being = new Predator(name, energy, cloningCost);
                break;
            default:
                being = new SimplePimpek(name, energy, cloningCost);
                break;
        }
        return being;
    }
}
