package co.ff.edu.quickstart.todos.adapters.out;

import co.ff.edu.quickstart.todos.application.model.Todo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ToDoDatabase {
    List<Todo> todos = IntStream.range(0, 100)
            .mapToObj(index -> {
                var name = "Task #" + index;
                var description = "Description #" + index;
                return Todo.from(index, name, description);
            })
            .collect(Collectors.toUnmodifiableList());

    public List<Todo> findAll() {
        return todos;
    }
}
