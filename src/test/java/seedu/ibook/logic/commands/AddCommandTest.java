package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;*/
import static seedu.ibook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.ibook.commons.core.GuiSettings;
//import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.IBook;
import seedu.ibook.model.Model;
import seedu.ibook.model.ReadOnlyIBook;
import seedu.ibook.model.ReadOnlyUserPrefs;
import seedu.ibook.model.product.Product;
//import seedu.ibook.testutil.ProductBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_productAcceptedByModel_addSuccessful() throws Exception {
        /*
        ModelStubAcceptingProductAdded modelStub = new ModelStubAcceptingProductAdded();
        Product validProduct = new ProductBuilder().build();

        CommandResult commandResult = new AddCommand(validProduct).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validProduct), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProduct), modelStub.productsAdded);

         */
    }

    @Test
    public void execute_duplicateProduct_throwsCommandException() {
        /*
        Product validProduct = new ProductBuilder().build();
        AddCommand addCommand = new AddCommand(validProduct);
        ModelStub modelStub = new ModelStubWithProduct(validProduct);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));

         */
    }

    @Test
    public void equals() {
        /*
        Product alice = new ProductBuilder().withName("Alice").build();
        Product bob = new ProductBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different product -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));

         */
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getIBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIBook(ReadOnlyIBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyIBook getIBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProduct(Product target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProduct(Product target, Product editedProduct) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Product> getFilteredProductList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProductList(Predicate<Product> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single product.
     */
    private class ModelStubWithProduct extends ModelStub {
        private final Product product;

        ModelStubWithProduct(Product product) {
            requireNonNull(product);
            this.product = product;
        }

        @Override
        public boolean hasProduct(Product product) {
            requireNonNull(product);
            return this.product.isSameProduct(product);
        }
    }

    /**
     * A Model stub that always accept the product being added.
     */
    private class ModelStubAcceptingProductAdded extends ModelStub {
        final ArrayList<Product> productsAdded = new ArrayList<>();

        @Override
        public boolean hasProduct(Product product) {
            requireNonNull(product);
            return productsAdded.stream().anyMatch(product::isSameProduct);
        }

        @Override
        public void addProduct(Product product) {
            requireNonNull(product);
            productsAdded.add(product);
        }

        @Override
        public ReadOnlyIBook getIBook() {
            return new IBook();
        }
    }

}