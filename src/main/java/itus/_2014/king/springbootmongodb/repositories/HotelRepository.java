package itus._2014.king.springbootmongodb.repositories;

import itus._2014.king.springbootmongodb.domain.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import  java.util.*;

@Repository
public interface HotelRepository  extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel> {
    Optional<Hotel> findById(String id);
    List<Hotel> findByPricePerNightIsLessThan(int maxPrice);

    @Query(value = "{address.city:?0}")
    List<Hotel> findByCity(String city);
}
