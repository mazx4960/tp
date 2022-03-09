package seedu.ibook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.ibook.model.product.Product;

public class ProductCard extends UiPart<HBox> {

    private static final String FXML = "ProductCard.fxml";

    private final int id;
    private final Product product;

    @FXML
    private Label index;
    @FXML
    private Label name;
    @FXML
    private Label category;
    @FXML
    private Label expiryDate;
    @FXML
    private Label price;
    @FXML
    private Label description;

    @FXML
    private HBox action;

    ProductCard(int index, Product product) {
        super(FXML);
        this.id = index;
        this.product = product;
        populateField();
    }

    void populateField() {
        index.setText(String.valueOf(id));
        name.setText(product.getName().toString());
        category.setText(product.getCategory().toString());
        expiryDate.setText(product.getExpiryDate().toString());
        price.setText(product.getPrice().toString());
        description.setText(product.getDescription().toString());
    }

}
