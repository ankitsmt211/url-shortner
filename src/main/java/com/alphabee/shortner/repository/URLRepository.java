package com.alphabee.shortner.repository;

import com.alphabee.shortner.URLEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends CrudRepository<URLEntity,String> {
}
