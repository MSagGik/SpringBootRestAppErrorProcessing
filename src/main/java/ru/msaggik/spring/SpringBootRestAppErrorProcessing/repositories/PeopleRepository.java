package ru.msaggik.spring.SpringBootRestAppErrorProcessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.msaggik.spring.SpringBootRestAppErrorProcessing.model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
