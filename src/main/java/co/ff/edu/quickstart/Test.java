package co.ff.edu.quickstart;

import co.ff.edu.quickstart.todos.application.model.TodosFilterQuantity;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Test {
    public static void main(String[] args) {
        /*Disposable subscribe = Flux.range(0, 199)
                .flatMap(integer -> {
                    String s = "text:" + integer;
                    return Flux.just(s)
                            .delayElements(Duration.of(integer * 100, ChronoUnit.MILLIS));
                })
                .subscribe(System.out::println);


        Thread.sleep(10000);
        subscribe.dispose();
        Thread.sleep(10000);*/

        var filterQ = Mono.fromSupplier(() -> TodosFilterQuantity.of(1))
                .flatMap(quantity -> Mono.error(new IllegalAccessError("Boom!")));

        filterQ.subscribe(
                System.out::println,
                e -> System.out.println("There was an error")
        );

        System.out.println("Hello world!");
    }
}
