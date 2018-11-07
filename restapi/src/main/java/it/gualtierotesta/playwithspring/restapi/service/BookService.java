package it.gualtierotesta.playwithspring.restapi.service;

import it.gualtierotesta.playwithspring.restapi.dto.Book;
import org.springframework.stereotype.Component;

@Component
public class BookService {

    public ServiceResult<Book> findBookById(String bookId) {
        // Not yet implemented
        return ServiceResult.failure();
    }
}
