package co.ff.edu.quickstart.todos.application.usecases;

import co.ff.edu.quickstart.todos.application.model.Todo;
import reactor.core.publisher.Flux;

public interface GetTodosUseCase {
    Flux<Todo> process();
}