package model.cellcontent;

public class Wall implements Content, Obstacle{
    private static final String IMAGE_PATH = "src/main/resources/img/wall.png";

    private static final Type TYPE = Type.WALL;
    private static final boolean ACCESSIBLE = false;

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
        return ACCESSIBLE;
    }

}
