package seedu.ibook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.ibook.model.IBook;
import seedu.ibook.model.ReadOnlyIBook;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.ExpiryDate;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Product[] getSampleProducts() {
        return new Product[] {
            new Product(new Name("Maggie Mee"), new Category("Noodles"),
                new ExpiryDate("2022-12-13"),
                new Description(""), new Price("1.99"))
        };
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

    public static ReadOnlyIBook getSampleIBook() {
        IBook sampleIb = new IBook();
        for (Product sampleProduct : getSampleProducts()) {
            sampleIb.addProduct(sampleProduct);
        }
        return sampleIb;
    }
}
