package co.ff.edu.quickstart.todos.adapters.out;

import co.ff.edu.quickstart.todos.application.model.Todo;
import co.ff.edu.quickstart.todos.application.model.TodosFilter;
import co.ff.edu.quickstart.todos.application.ports.out.GetTodosByFilterPort;
import co.ff.edu.quickstart.todos.application.ports.out.GetTodosPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

// adapter

@Repository
public class ToDosRepositoryAdapter implements GetTodosPort, GetTodosByFilterPort {
    private final ToDoDatabase database;

    @Autowired
    public ToDosRepositoryAdapter(ToDoDatabase database) {
        this.database = database;
    }


    @Override
    public Flux<Todo> getAllTodos() {
        return Flux.fromIterable(database.findAll());
    }

    @Override
    public Flux<Todo> getTodosByFilter(TodosFilter filter) {
        return Flux.fromIterable(database.findAll())
                .take(filter.getQuantity().getValue());
    }
}
