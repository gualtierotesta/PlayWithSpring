package it.gualtierotesta.playwithspring.restapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class MyData {

    @NotNull
    @Size(min = 3, max = 255, message = "Name should be at leat 3 chars, max 255")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past(message = "Birth date should be in the past")
    private LocalDate birthDate;

}
