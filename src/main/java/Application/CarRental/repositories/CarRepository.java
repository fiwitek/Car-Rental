package Application.CarRental.repositories;

import Application.CarRental.models.Car;
import Application.CarRental.models.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Rank> findByLicense(String name);
}
