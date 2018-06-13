package model.pimpek;

public class Predator extends SimplePimpek {


    public Predator(String name, int energy, int cloningCost) {
        super(name, energy, cloningCost);
    }

    public Predator(Pimpek ancestor, String name, int energy, int cloningCost) {
        super(ancestor, name, energy, cloningCost);
    }

    @Override
    public void act() {

    }


}
