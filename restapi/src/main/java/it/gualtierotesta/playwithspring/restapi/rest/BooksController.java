package it.gualtierotesta.playwithspring.restapi.rest;

import com.google.common.base.CharMatcher;
import it.gualtierotesta.playwithspring.restapi.dto.Book;
import it.gualtierotesta.playwithspring.restapi.service.BookService;
import it.gualtierotesta.playwithspring.restapi.service.ServiceResult;
import it.gualtierotesta.playwithspring.restapi.type.BookId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/books")
@Slf4j
public class BooksController {

    private BookService service;

    public BooksController(final BookService service) {
        this.service = service;
    }

    @GetMapping("/{bookId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity getBook(
            @PathVariable(value = "bookId", required = true) final String bookId,
            final Authentication authentication) {

//        if (requestNotAuthorized(authentication)) {
//            return ResponseEntity.status(UNAUTHORIZED).build();
//        }
        if (requestIsNotValid(bookId)) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }

        ServiceResult<Book> serviceResult = service.findBookById(bookId);

        return serviceResult.isSuccess() ?
                ResponseEntity.ok(RestResult.success(serviceResult.data())) :
                ResponseEntity.ok(RestResult.failure(9, "Could not process request"));
    }

    private boolean requestIsNotValid(final String bookId) {
        return !BookId.isValid(bookId);
    }

    private boolean requestNotAuthorized(final Authentication authentication) {
        // User should be authenticated and have role USER
        return !authentication.isAuthenticated() ||
                !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
    }

}