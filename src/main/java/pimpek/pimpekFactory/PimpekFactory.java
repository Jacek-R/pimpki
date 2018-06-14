package pimpek.pimpekFactory;

import pimpek.pimpekModel.PimpekGenre;
import pimpek.pimpekModel.Pimpek;

public interface PimpekFactory {

    Pimpek create(PimpekGenre genre);
    Pimpek clone(Pimpek parent);
}
