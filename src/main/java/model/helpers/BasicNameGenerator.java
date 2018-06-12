package model.helpers;

import model.pimpek.Genre;

public class BasicNameGenerator implements NameGenerator {


    @Override
    public String generate(Genre genre) {

        return "Pimp!";
    }
}
