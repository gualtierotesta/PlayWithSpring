package it.gualtierotesta.playwithspring.restapi.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Book {

    private String id;
    private String title;
    private String author;
    private LocalDate publishingDate;
}
