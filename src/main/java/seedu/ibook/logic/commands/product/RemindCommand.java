package seedu.ibook.logic.commands.product;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.logic.commands.Command;
import seedu.ibook.logic.commands.CommandResult;
import seedu.ibook.model.Model;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.model.product.filters.ExpiringFilter;

public class RemindCommand extends Command {
    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_SUCCESS = "Listed expiring products.\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists products with their items that are expiring "
            + "within the specified number of days\n"
            + "Parameters: DAYS (Must be non negative)\n"
            + "Example: remind 10";

    private final AttributeFilter expiringFilter;

    private final ExpiryDate expiryDate;

    private final Predicate<Item> itemPredicate = new Predicate<>() {
        @Override
        public boolean test(Item item) {
            return item.expiresBefore(expiryDate);
        }
    };

    /**
     * Creates a reminder command with {@code expiryDate}.
     * @param expiryDate
     */
    public RemindCommand(ExpiryDate expiryDate) {
        this.expiryDate = expiryDate;
        expiringFilter = new ExpiringFilter(expiryDate);
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearProductFilters();
        model.addProductFilter(expiringFilter);
        model.updateFilteredItemListForProducts(itemPredicate);
        return new CommandResult(MESSAGE_SUCCESS
                + String.format(Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW, model.getFilteredProductList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RemindCommand)) {
            return false;
        }

        assert expiryDate != null;
        return expiryDate.equals(((RemindCommand) other).expiryDate);
    }
}
