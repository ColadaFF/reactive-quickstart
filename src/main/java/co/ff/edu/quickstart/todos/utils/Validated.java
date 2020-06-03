package co.ff.edu.quickstart.todos.utils;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public interface Validated<T> {
    Validation<Seq<String>, T> validate();
}
