package com.example.sakila_db;

import jakarta.validation.groups.Default;

public final class ValidationGroup {

    public interface Create extends Default{}
    public interface Update extends Default {}
}
