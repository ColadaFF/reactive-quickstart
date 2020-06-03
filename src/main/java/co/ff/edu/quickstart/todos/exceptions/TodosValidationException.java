package co.ff.edu.quickstart.todos.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value(staticConstructor = "of")
public class TodosValidationException extends RuntimeException {
    String message;
}
