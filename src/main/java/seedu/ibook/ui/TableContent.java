package seedu.ibook.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import seedu.ibook.model.product.Product;

public class TableContent extends UiPart<ScrollPane> {

    private static final String FXML = "TableContent.fxml";

    private final ObservableList<Product> filteredIBook;

    @FXML
    private VBox content;

    TableContent(ObservableList<Product> filteredIBook) {
        super(FXML);
        this.filteredIBook = filteredIBook;
        this.filteredIBook.addListener(new Listener());
        populateField();
    }

    void populateField() {
        for (int i = 0; i < filteredIBook.size(); i++) {
            Product product = filteredIBook.get(i);
            ProductCard productCard = new ProductCard(i + 1, product);
            content.getChildren().add(productCard.getRoot());
        }
    }

    private class Listener implements ListChangeListener<Product> {
        @Override
        public void onChanged(Change<? extends Product> c) {
            populateField();
        }
    }

}
