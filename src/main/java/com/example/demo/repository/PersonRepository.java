package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.*;
import com.example.demo.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {}
