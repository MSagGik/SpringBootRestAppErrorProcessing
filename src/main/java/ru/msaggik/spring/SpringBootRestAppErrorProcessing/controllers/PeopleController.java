package ru.msaggik.spring.SpringBootRestAppErrorProcessing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.msaggik.spring.SpringBootRestAppErrorProcessing.model.Person;
import ru.msaggik.spring.SpringBootRestAppErrorProcessing.services.PeopleService;
import ru.msaggik.spring.SpringBootRestAppErrorProcessing.util.PersonErrorResponse;
import ru.msaggik.spring.SpringBootRestAppErrorProcessing.util.PersonNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    // метод возврата данных всех пользователей
    @GetMapping()
    public List<Person> getPeople() {
        // статус - 200
        // Jackson конвертирует возвращаемые объекты в JSON
        return peopleService.findAll();
    }
    // метод возврата данных одного пользователя по id
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        // статус - 200
        // Jackson конвертирует возвращаемый объект в JSON
        return peopleService.findOne(id);
    }
    // метод обработки исключения
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {

        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found!",
                System.currentTimeMillis()
        );
        // в HTTP ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404 статус
    }
}
