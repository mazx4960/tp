package seedu.ibook.ui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import seedu.ibook.model.product.Product;

public class Table extends UiPart<VBox> {

    private static final String FXML = "Table.fxml";

    private TableHeader tableHeader;
    private TableContent tableContent;

    private final ObservableList<Product> filteredIBook;

    Table(ObservableList<Product> filteredIBook) {
        super(FXML);
        this.filteredIBook = filteredIBook;
        populateField();
    }

    void populateField() {
        ObservableList<Node> children = getRoot().getChildren();

        tableHeader = new TableHeader();
        children.add(tableHeader.getRoot());

        tableContent = new TableContent(filteredIBook);
        children.add(tableContent.getRoot());
    }
}
