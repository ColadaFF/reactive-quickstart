package co.ff.edu.quickstart.todos.adapters.in;

import co.ff.edu.quickstart.todos.exceptions.TodosValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class TodosEntryPoint {

    @Bean
    RouterFunction<ServerResponse> todoRouter(TodosHandler handler) {
        return RouterFunctions.route()
                .nest(path("/todos-router"), builder -> {
                    builder
                            .GET("", handler::getAllTodos)
                            .GET("/{id}", request -> ok().build());
                })
                .onError(RuntimeException.class, (e, request) -> badRequest().build())
                .onError(IllegalArgumentException.class, (e, request) -> badRequest().build())
                .onError(TodosValidationException.class, (e, request) -> {
                    return badRequest()
                            .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                            .body(Mono.just(e.getMessage()), String.class);
                })
                .build();
    }
}
