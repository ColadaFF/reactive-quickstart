package co.ff.edu.quickstart.todos.adapters.in;

import co.ff.edu.quickstart.todos.application.model.Todo;
import co.ff.edu.quickstart.todos.application.model.TodosFilter;
import co.ff.edu.quickstart.todos.application.model.TodosFilterQuantity;
import co.ff.edu.quickstart.todos.application.usecases.GetTodosByFilterUseCase;
import co.ff.edu.quickstart.todos.application.usecases.GetTodosUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDosController {
    private final GetTodosUseCase getTodosUseCase;
    private final GetTodosByFilterUseCase getByFilter;


    @GetMapping
    public Flux<Todo> getAllTodos() {
        return getTodosUseCase.process();
    }

    @GetMapping("/filter")
    public Flux<Todo> getAllTodosByFilter(
            @RequestParam(name = "quantity", defaultValue = "10") Integer quantity
    ) {
        var quantityDomain = TodosFilterQuantity.of(quantity);
        var filter = TodosFilter.from(quantityDomain);
        var query = GetTodosByFilterUseCase.Query.of(filter);
        return getByFilter.process(query);
    }
}
