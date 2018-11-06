package it.gualtierotesta.playwithspring.restapi.type;

import com.google.common.base.CharMatcher;

public class BookId {

    private final String id;  // internal representation


    public static BookId of(final String id) {
        if (isValid(id)) {
            return new BookId(id);
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }

    public static boolean isValid(String id) {
        boolean valid = false;
        if (id != null && !id.isEmpty()) {
            String onlyDigits = CharMatcher.inRange('0', '9').retainFrom(id);
            valid = onlyDigits.equals(id) && Integer.valueOf(onlyDigits) > 0;
        }
        return valid;
    }

    public String asString() {
        return id;
    }

    private BookId(String id) {
        this.id = id;
    }
}
