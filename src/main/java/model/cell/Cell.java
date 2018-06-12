package model.cell;

import model.cellcontent.Content;

public class Cell {

    private CellView cellView;
    private Content content;

    public Cell(CellView cellView, Content content) {
        this.cellView = cellView;
        this.content = content;
    }

    public CellView getCellView() {
        return cellView;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }
}
