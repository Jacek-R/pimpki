package food.foodModel;

import cell.cellcontent.Content;

public interface Food extends Content {

    int getEnergy();

    FoodGenre getGenre();
}
