package model.helpers;

import model.pimpek.PimpekGenre;

public class BasicNameGenerator implements NameGenerator {


    @Override
    public String generate(PimpekGenre genre) {

        return "Pimp!";
    }
}
