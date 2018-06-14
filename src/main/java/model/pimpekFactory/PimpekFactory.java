package model.pimpekFactory;

import model.pimpek.PimpekGenre;
import model.pimpek.Pimpek;

public interface PimpekFactory {

    Pimpek create(PimpekGenre genre);
    Pimpek clone(Pimpek parent);
}
