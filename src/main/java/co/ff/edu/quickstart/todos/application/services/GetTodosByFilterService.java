package co.ff.edu.quickstart.todos.application.services;

import co.ff.edu.quickstart.todos.application.model.Todo;
import co.ff.edu.quickstart.todos.application.ports.out.GetTodosByFilterPort;
import co.ff.edu.quickstart.todos.application.usecases.GetTodosByFilterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetTodosByFilterService  implements GetTodosByFilterUseCase {
    private final GetTodosByFilterPort port;

    @Override
    public Flux<Todo> process(Query query) {
        return port.getTodosByFilter(query.getFilter());
    }
}
