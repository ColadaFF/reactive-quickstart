package co.ff.edu.quickstart.todos.application.services;

import co.ff.edu.quickstart.todos.application.model.Todo;
import co.ff.edu.quickstart.todos.application.ports.out.GetTodosPort;
import co.ff.edu.quickstart.todos.application.usecases.GetTodosUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetTodosService implements GetTodosUseCase {
    private final GetTodosPort port;

    @Override
    public Flux<Todo> process() {
        return port.getAllTodos();
    }
}
