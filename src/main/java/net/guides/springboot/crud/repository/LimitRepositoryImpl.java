package net.guides.springboot.crud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import net.guides.springboot.crud.model.Passlimit;
import java.util.List;
public class LimitRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate; // we will use this to query mongoDb

    public List<Passlimit> findByLimitDate(String limitDate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("reviews.limitDate").is(limitDate));
        query.fields().include("reviews.$");
        return mongoTemplate.find(query, Passlimit.class);
    }
}
