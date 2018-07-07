package itus._2014.king.springbootmongodb.seed;

import itus._2014.king.springbootmongodb.domain.Address;
import itus._2014.king.springbootmongodb.domain.Hotel;
import itus._2014.king.springbootmongodb.domain.Review;
import itus._2014.king.springbootmongodb.repositories.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    HotelRepository hotelRepository;

    public DbSeeder(HotelRepository hotelRepository)
    {
        this.hotelRepository = hotelRepository;
    }
    @Override
    public void run(String...strings) throws Exception {
        Hotel one_only = new Hotel(
                "One&Only",
                199,
                new Address("Cap Town", "South Africa"),
                Arrays.asList(new Review("Harris", 8, false),
                        new Review("Kane", 7, true))
        );

        Hotel CLAYOQUOT = new Hotel(
                "CLAYOQUOT WILDERNESS RESORT",
                338,
                new Address("Vancouver Island", "Canada"),
                Arrays.asList(new Review("Jessi", 10, true),
                        new Review("Alice", 7, true))
        );

        Hotel sofitel = new Hotel(
                "AMANGIRI",
                440,
                new Address("UTAH", "USA"),
                new ArrayList<>()
        );

        this.hotelRepository.deleteAll();

        List<Hotel> hotels = Arrays.asList(one_only, CLAYOQUOT, sofitel);
        for (Hotel hotel : hotels)
        {
            this.hotelRepository.save(hotel);
        }
    }
}
