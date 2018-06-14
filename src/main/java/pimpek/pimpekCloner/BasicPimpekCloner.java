package pimpek.pimpekCloner;

import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekFactory.PimpekFactory;

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
