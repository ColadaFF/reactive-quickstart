package co.ff.edu.quickstart.todos.application.ports.out;

import co.ff.edu.quickstart.todos.application.model.Todo;
import co.ff.edu.quickstart.todos.application.model.TodosFilter;
import reactor.core.publisher.Flux;

public interface GetTodosByFilterPort {
    Flux<Todo> getTodosByFilter(TodosFilter filter);
}
