package co.ff.edu.quickstart.todos.application.model;

import co.ff.edu.quickstart.todos.utils.ValidationUtils;
import io.vavr.control.Validation;
import lombok.Value;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Value(staticConstructor = "of")
public class TodosFilterQuantity {
    public static Validation<List<String>, TodosFilterQuantity> create(Integer value) {
        return ValidationUtils.generateValidation(
                List.of(
                        validateNotNull(value),
                        positiveNumber(value)
                ),
                TodosFilterQuantity::new
        );
    }

    Integer value;

    private TodosFilterQuantity(Integer value) {
        Validate.notNull(value, "Quantity can not be null");
        Validate.isTrue(value >= 0, "Quantity must be greater than 0");
        Validate.isTrue(value <= 10, "Quantity must be less than 10");
        this.value = value;
    }


    private static Validation<String, Integer> validateNotNull(Integer value) {
        return Objects.isNull(value) ?
                Validation.invalid("Quantity can not be null")
                : Validation.valid(value);
    }

    private static Validation<String, Integer> positiveNumber(Integer value) {
        return value >= 0 ?
                Validation.invalid("Quantity must be greater than 0")
                : Validation.valid(value);
    }
}
