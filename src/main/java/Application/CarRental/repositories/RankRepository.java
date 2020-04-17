package Application.CarRental.repositories;

import Application.CarRental.models.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank,Long> {
    Optional<Rank> findByName(String name);
}
