package it.gualtierotesta.playwithspring.restapi.rest;

import it.gualtierotesta.playwithspring.restapi.dto.MyData;
import it.gualtierotesta.playwithspring.restapi.service.MyService;
import it.gualtierotesta.playwithspring.restapi.service.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/home")
       // consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE},
       // produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
@Slf4j
public class HomeController {

    private MyService service;

    @Autowired
    public HomeController(MyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> get(final HttpServletRequest request) {
        if (requestNotAuthorized(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ServiceResult<String> serviceResult = service.doGet();

        if (serviceResult.isSuccess()) {
            return new ResponseEntity<>(
                    RestResult.success(serviceResult.data()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    RestResult.failure(9, "Could not process request"),
                    HttpStatus.OK);
        }
    }


    @PostMapping
    public ResponseEntity<?> post(
            @RequestBody final @Valid MyData data,
            final Errors errors,
            final HttpServletRequest request) {

        if (requestNotAuthorized(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (errors.hasErrors()) {
            log.info("Invalid data: data={} errors={}", data, errors.getAllErrors());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ServiceResult<MyData> serviceResult = service.doPost(data);

        if (serviceResult.isSuccess()) {
            return new ResponseEntity<>(
                    RestResult.success(serviceResult.data()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    RestResult.failure(9, "Could not process request"),
                    HttpStatus.OK);
        }
    }


    private boolean requestNotAuthorized(HttpServletRequest request) {
        log.info("UserPrincipal={}", request.getUserPrincipal());
        return false;
    }
}
