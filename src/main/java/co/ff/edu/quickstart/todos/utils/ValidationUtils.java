package co.ff.edu.quickstart.todos.utils;

import co.ff.edu.quickstart.todos.application.model.TodosFilterQuantity;
import io.vavr.control.Validation;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class ValidationUtils {

    public static <T, U> Validation<List<String>, T> generateValidation(
            List<Validation<String, U>> validations,
            Function<U, T> creator
    ) {
        boolean anyFail = validations
                .stream()
                .anyMatch(Validation::isInvalid);
        if(anyFail) {
            List<String> errors = validations.stream()
                    .filter(Validation::isInvalid)
                    .map(Validation::getError)
                    .collect(Collectors.toList());
            return Validation.invalid(errors);
        }
        return validations.stream().findFirst()
                .map(Validation::get)
                .map(creator)
                .map(Validation::<List<String>, T>valid)
                .get();
    }
}
