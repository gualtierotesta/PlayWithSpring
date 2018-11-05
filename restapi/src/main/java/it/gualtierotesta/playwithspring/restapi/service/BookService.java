package it.gualtierotesta.playwithspring.restapi.service;

import it.gualtierotesta.playwithspring.restapi.dto.Book;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookService {

    public ServiceResult<Book> findBookById(String bookId) {
        return ServiceResult.success(Book.builder()
                .id(bookId).author("Gualtiero").title("Titolo").publishingDate(LocalDate.now().minusYears(13))
                .build());
    }
}
