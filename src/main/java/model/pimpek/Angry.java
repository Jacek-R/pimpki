package model.pimpek;

import explorer.WorldExplorer;
import model.cellcontent.Type;
import world.Board;

public class Angry extends SimplePimpek implements Predator {

    private static final String IMAGE_PATH = "src/main/resources/img/pimpek.png";
    private static final Type TYPE = Type.PREDATOR;


    public Angry(String name, int energy, int cloningCost, WorldExplorer explorer) {
        super(name, energy, cloningCost, explorer);
    }

    public Angry(Pimpek ancestor, String name, int energy, int cloningCost, WorldExplorer explorer) {
        super(ancestor, name, energy, cloningCost, explorer);
    }

    @Override
    public void act(Board world) {

    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }

    @Override
    public Type getType() {
        return TYPE;
    }

    @Override
    public boolean isAccessible() {
        return true;
    }


}
