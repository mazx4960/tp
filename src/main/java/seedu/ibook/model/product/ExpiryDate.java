package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents a Product's expiry date in the ibook.
 * Guarantees: immutable;
 */
public class ExpiryDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Expiry dates should have format such as 03 May 2022, 3 May 2022 or 2022-05-03";

    private static final DateStringManager[] ACCEPTED_FORMATS = {
        new DateStringManager("d MMM yyyy"),
        new DateStringManager("dd MMM yyyy"),
        new DateStringManager("yyyy-MM-dd")
    };

    private static final DateStringManager DEFAULT_DATE_MANAGER = ACCEPTED_FORMATS[0];

    public final LocalDate expiryDate;
    private final DateStringManager dateStringManager;

    /**
     * Constructs a {@code ExpiryDate}.
     *
     * @param date A valid date.
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidExpiryDate(date), MESSAGE_CONSTRAINTS);

        dateStringManager = findDateStringManager(date);
        expiryDate = dateStringManager.parse(date);
    }

    /**
     * Returns true if a given {@code LocalDate} is a valid expiry date.
     */
    public static boolean isValidExpiryDate(String expiryDate) {
        return Arrays.stream(ACCEPTED_FORMATS)
                .anyMatch(manager -> manager.matches(expiryDate));
    }

    public boolean isPast() {
        return expiryDate.isBefore(LocalDate.now());
    }

    /**
     * Find first {@code DateStringManager} object that matches the given date string.
     * @param date Date string
     * @return First {@code DateStringManager} in {@code ACCEPTED_FORMATS} that matches {@code date}.
     */
    private DateStringManager findDateStringManager(String date) {
        return Arrays.stream(ACCEPTED_FORMATS)
                .filter(manager -> manager.matches(date))
                .findFirst()
                .get();
    }

    @Override
    public String toString() {
        return DEFAULT_DATE_MANAGER.parseString(expiryDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDate // instanceof handles nulls
                && expiryDate.equals(((ExpiryDate) other).expiryDate)); // state check
    }

    @Override
    public int hashCode() {
        return expiryDate.hashCode();
    }

    /**
     * Class that encapsulates date string pattern matching and parsing
     */
    private static class DateStringManager {

        private String format;
        private DateTimeFormatter dateFormatter;

        public DateStringManager(String format) {
            this.format = format;
            dateFormatter = DateTimeFormatter.ofPattern(format);
        }

        public boolean matches(String test) {
            try {
                LocalDate.parse(test, dateFormatter);
            } catch (DateTimeParseException pe) {
                return false;
            }

            return true;
        }

        public LocalDate parse(String date) {
            return LocalDate.parse(date, dateFormatter);
        }

        public String parseString(LocalDate date) {
            return date.format(dateFormatter);
        }

    }

}
