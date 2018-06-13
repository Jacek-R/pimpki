package model.pimpekCloner;

import model.pimpek.Pimpek;
import model.pimpekFactory.PimpekFactory;

public class BasicPimpekCloner implements PimpekCloner {

    private final PimpekFactory factory;

    public BasicPimpekCloner(PimpekFactory factory) {
        this.factory = factory;
    }

    @Override
    public Pimpek clone(Pimpek parent) {
        return factory.clone(parent);
    }
}
