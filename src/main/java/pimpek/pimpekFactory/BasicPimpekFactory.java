package pimpek.pimpekFactory;

import worldManager.WorldManager;
import configuration.Configuration;
import helpers.nameGenerator.NameGenerator;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekModel.PimpekGenre;
import pimpek.pimpekModel.Angry;
import pimpek.pimpekModel.SimplePimpek;

public class BasicPimpekFactory implements PimpekFactory {

    private final NameGenerator nameGenerator;
    private final Configuration configuration;
    private final WorldManager worldManager;

    public BasicPimpekFactory(NameGenerator nameGenerator, Configuration configuration, WorldManager worldManager) {
        this.nameGenerator = nameGenerator;
        this.configuration = configuration;
        this.worldManager = worldManager;
    }


    @Override
    public Pimpek create(PimpekGenre genre) {

        String name = nameGenerator.generate(genre);
        int energy = configuration.getInitialEnergy();
        int cloningCost = configuration.getCloningCost();
        Pimpek being;
        switch (genre) {
            case PREDATOR:
                being = new Angry(name, energy, cloningCost, worldManager);
                break;
            default:
                being = new SimplePimpek(name, energy, cloningCost, worldManager);
                break;
        }
        return being;
    }

    @Override
    public Pimpek clone(Pimpek parent) {

        PimpekGenre genre = parent.getGenre();
        String name = nameGenerator.generate(genre);
        int cloningCost = configuration.getCloningCost();

        int energy = configuration.getInitialEnergy() - cloningCost;

        Pimpek being;

        switch (genre) {
            case PREDATOR:
                being = new Angry(parent.getAncestor(), name, energy, cloningCost, worldManager);
                break;
            default:
                being = new SimplePimpek(parent.getAncestor(), name, energy, cloningCost, worldManager);
                break;
        }
        return being;
    }
}
