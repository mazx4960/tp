package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ibook.model.Model;
import seedu.ibook.model.product.Product;

/**
 * Lists all products in the ibook to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed products";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearProductFilters();
        model.updateFilteredItemListForProducts(Product.PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
