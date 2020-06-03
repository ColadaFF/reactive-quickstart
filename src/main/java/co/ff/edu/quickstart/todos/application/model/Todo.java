package co.ff.edu.quickstart.todos.application.model;

import lombok.Value;

@Value(staticConstructor = "from")
public class Todo {
    Integer id;
    String name;
    String description;
}
