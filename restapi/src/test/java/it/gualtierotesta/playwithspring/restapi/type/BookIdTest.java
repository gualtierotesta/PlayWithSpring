package it.gualtierotesta.playwithspring.restapi.type;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookIdTest {

    @Test
    public void testValid() {
        assertThat(BookId.isValid(null)).isFalse();
        assertThat(BookId.isValid("")).isFalse();
        assertThat(BookId.isValid("ciao")).isFalse();
        assertThat(BookId.isValid("0")).isFalse();

        assertThat(BookId.isValid("123")).isTrue();
    }
}