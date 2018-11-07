package it.gualtierotesta.playwithspring.restapi.rest;

import com.google.common.base.CharMatcher;
import it.gualtierotesta.playwithspring.restapi.dto.Book;
import it.gualtierotesta.playwithspring.restapi.service.BookService;
import it.gualtierotesta.playwithspring.restapi.service.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/books")
@Slf4j
public class BooksController {

    private BookService service;

    public BooksController(final BookService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> saveBook(
            @RequestBody final Book book) {

        log.info("post: book={}", book);
        return ResponseEntity.ok(RestResult.success("ok"));
    }


    @GetMapping("/{bookId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getBook(
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

    private static boolean requestIsNotValid(final String bookId) {
        boolean notValid = true;
        if (bookId != null && !bookId.isEmpty()) {
            String onlyDigits = CharMatcher.inRange('0', '9').retainFrom(bookId);
            notValid = !onlyDigits.equals(bookId) || Integer.valueOf(onlyDigits) <= 0;
        }
        return notValid;
    }

    private static boolean requestNotAuthorized(final Authentication authentication) {
        // User should be authenticated and have role USER
        return !authentication.isAuthenticated() ||
                !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
    }

}
