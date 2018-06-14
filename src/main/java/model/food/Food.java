package model.food;

import model.cellcontent.Content;

public interface Food extends Content {

    int getEnergy();

    FoodGenre getGenre();
}
