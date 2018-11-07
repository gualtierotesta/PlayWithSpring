package it.gualtierotesta.playwithspring.restapi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class Book {

    private String id;

    @NotNull
    @Size(min = 3, max=255)
    private String title;

    @NotNull
    @Size(min=2, max=255)
    private String author;

    @PastOrPresent
    private LocalDate publishingDate;
}
