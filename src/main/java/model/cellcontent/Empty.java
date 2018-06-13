package model.cellcontent;

public class Empty implements Content {
    private static final String IMAGE_PATH = "src/main/resources/img/empty.png";

    private static final Type TYPE = Type.EMPTY;
    private static final boolean ACCESSIBLE = true;

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
