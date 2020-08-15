	package net.guides.springboot.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot.crud.model.Passlimit;

@Repository
public interface PasslimitRepository extends MongoRepository<Passlimit, Long>{

}
