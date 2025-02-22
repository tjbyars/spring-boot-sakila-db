package com.example.sakila_db;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActorInput {
    @NotNull(groups = {ValidationGroup.Create.class})
    @Size(min = 1, max = 45)
    private String firstName;

    @NotNull(groups = ValidationGroup.Create.class)
    @Size(min = 1, max = 45)
    private String lastName;

    public ActorInput(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
