package co.ff.edu.quickstart.todos.application.ports.out;

import co.ff.edu.quickstart.todos.application.model.Todo;
import reactor.core.publisher.Flux;

public interface GetTodosPort {
    Flux<Todo> getAllTodos();
}
