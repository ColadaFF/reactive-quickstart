package co.ff.edu.quickstart.todos.application.usecases;

import co.ff.edu.quickstart.todos.application.model.Todo;
import co.ff.edu.quickstart.todos.application.model.TodosFilter;
import co.ff.edu.quickstart.todos.application.model.TodosFilterQuantity;
import lombok.Value;
import reactor.core.publisher.Flux;

public interface GetTodosByFilterUseCase {
    Flux<Todo> process(Query query);

    @Value(staticConstructor = "of")
    class Query {
        TodosFilter filter;
    }
}
