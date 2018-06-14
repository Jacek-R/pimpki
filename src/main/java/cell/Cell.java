package cell;

import cell.cellcontent.Content;

public interface Cell {
    CellView getCellView();

    void setContent(Content content);

    Content getContent();
}
