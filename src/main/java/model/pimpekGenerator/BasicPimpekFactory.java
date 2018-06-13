package model.pimpekGenerator;

import explorer.WorldExplorer;
import model.configuration.Configuration;
import model.helpers.NameGenerator;
import model.pimpek.Pimpek;
import model.pimpek.PimpekGenre;
import model.pimpek.Angry;
import model.pimpek.SimplePimpek;

public class BasicPimpekFactory implements PimpekFactory {

    private final NameGenerator nameGenerator;
    private final Configuration configuration;
    private final WorldExplorer explorer;

    public BasicPimpekFactory(NameGenerator nameGenerator, Configuration configuration, WorldExplorer explorer) {
        this.nameGenerator = nameGenerator;
        this.configuration = configuration;
        this.explorer = explorer;
    }


    @Override
    public Pimpek create(PimpekGenre genre) {

        String name = nameGenerator.generate(genre);
        int energy = configuration.getInitialEnergy();
        int cloningCost = configuration.getCloningCost();
        Pimpek being;
        switch (genre) {
            case PREDATOR:
                being = new Angry(name, energy, cloningCost, explorer);
                break;
            default:
                being = new SimplePimpek(name, energy, cloningCost, explorer);
                break;
        }
        return being;
    }
}
