package my.web.app.Day03Rest01.controllers;

import my.web.app.Day03Rest01.models.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("rest/v1/men")
public class PersonController {
    private final List<Person> persons = Stream.of(
            new Person(1L,"Alex","Petrov","DevOps",120000),
            new Person(2L,"Ivan","Sidorov","Developer",220000),
            new Person(3L,"Sergey","Barkov","Analyst",200000)
    ).collect(Collectors.toList());

    @GetMapping("")
    public List<Person> getPersons() {
        return persons;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return persons.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping()
    public Person addPerson(@RequestBody Person person) {
        persons.add(person);
        return person;
    }

    @PutMapping()
    public Person changePerson(@RequestBody Person person) {
        return persons.stream()
                .filter(man -> man.getId().equals(person.getId()))
                .findFirst()
                .map(oldPerson -> {
                    oldPerson.setFirstname(person.getFirstname());
                    oldPerson.setLastname(person.getLastname());
                    oldPerson.setSalary(person.getSalary());
                    oldPerson.setPosition(person.getPosition());
                    return oldPerson;
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        persons.removeIf(item -> item.getId() == id);
    }

}
