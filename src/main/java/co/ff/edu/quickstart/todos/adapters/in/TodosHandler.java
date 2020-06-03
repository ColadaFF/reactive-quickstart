package co.ff.edu.quickstart.todos.adapters.in;

import co.ff.edu.quickstart.todos.application.model.Todo;
import co.ff.edu.quickstart.todos.application.model.TodosFilter;
import co.ff.edu.quickstart.todos.application.model.TodosFilterQuantity;
import co.ff.edu.quickstart.todos.application.usecases.GetTodosByFilterUseCase;
import co.ff.edu.quickstart.todos.exceptions.TodosValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class TodosHandler {
    private final GetTodosByFilterUseCase getByFilter;

    Mono<ServerResponse> getAllTodos(ServerRequest request) {
        Integer quantity = request.queryParam("quantity")
                .map(Integer::parseInt)
                .orElse(10);
        return Mono.just(quantity)
                .flatMap(integer -> {
                    return Mono.fromSupplier(() -> TodosFilterQuantity.of(integer))
                            .onErrorMap(throwable -> {
                                return TodosValidationException.of(throwable.getMessage());
                            });
                })
                .map(TodosFilter::from)
                .map(GetTodosByFilterUseCase.Query::of)
                .flatMap(query -> {
                    Flux<Todo> process = getByFilter.process(query);
                    return ok().body(process, Todo.class);
                });
    }
}
