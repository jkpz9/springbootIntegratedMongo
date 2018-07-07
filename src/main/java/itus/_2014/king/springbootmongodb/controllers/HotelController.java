package itus._2014.king.springbootmongodb.controllers;

import itus._2014.king.springbootmongodb.domain.Hotel;
import itus._2014.king.springbootmongodb.domain.QHotel;
import itus._2014.king.springbootmongodb.repositories.HotelRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository)
    {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/")
    public List<Hotel> getAll()
    {
        return this.hotelRepository.findAll();
    }


    @GetMapping("{id}")
    public Hotel getSingle(@PathVariable("id") String id)
    {
        return this.hotelRepository.findById(id).orElse(null);
    }

    @PutMapping
    public void update(@RequestBody Hotel hotel)
    {
        this.hotelRepository.insert(hotel);
    }

    @PostMapping
    public void insert(@RequestBody Hotel hotel)
    {
        this.hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id)
    {
         this.hotelRepository.deleteById(id);
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getByPricePerNight(@PathVariable("maxPrice") int maxPrice)
    {
        return this.hotelRepository.findByPricePerNightIsLessThan(maxPrice);
    }


    @GetMapping("/city/{city}")
    public List<Hotel> findByCity(@PathVariable("city") String city)
    {
        return this.hotelRepository.findByCity(city);
    }

    @GetMapping("/country/{country}")
    public List< Hotel> getByCountry(@PathVariable("country") String country)
    {
        // 1.
        QHotel qHotel = new QHotel("hotel");

        // 2.
        BooleanExpression filterByCountry = qHotel.address.country.eq(country);

        // 3
        return (List<Hotel>) this.hotelRepository.findAll(filterByCountry);
    }

    @GetMapping("/recommended")
    public List<Hotel> getRecommended(@RequestParam("maxPrice") int maxPrice, @RequestParam("minRate") int minRating)
    {
//        final int maxPrice = 200;
//        final int minRating = 7;

        QHotel qHotel = new QHotel("hotel");

        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);

        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating);

        return (List<Hotel>) hotelRepository.findAll(filterByPrice.and(filterByRating));
    }
}
