package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's category in the ibook.
 * Guarantees: immutable; is valid as declared in {@link #isValidCategoryName(String)}
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS =
            "Categories (if given) should only contain alphanumeric characters and spaces";

    /*
     * Empty strings are allowed. Otherwise, the first character of the description
     * must not be a whitespace
     */
    public static final String VALIDATION_REGEX = "(|[\\p{Alnum}][\\p{Alnum} ]*)";

    public final String fullCategoryName;

    /**
     * Constructs a {@code Category}.
     *
     * @param categoryName A valid category name.
     */
    public Category(String categoryName) {
        requireNonNull(categoryName);
        checkArgument(isValidCategoryName(categoryName), MESSAGE_CONSTRAINTS);
        fullCategoryName = categoryName;
    }

    /**
     * Returns true if a given string is a valid category name.
     */
    public static boolean isValidCategoryName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullCategoryName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && fullCategoryName.equals(((Category) other).fullCategoryName)); // state check
    }

    @Override
    public int hashCode() {
        return fullCategoryName.hashCode();
    }

}
