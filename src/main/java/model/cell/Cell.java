package model.cell;

import model.cellcontent.Content;

public interface Cell {
    CellView getCellView();

    void setContent(Content content);

    Content getContent();
}
