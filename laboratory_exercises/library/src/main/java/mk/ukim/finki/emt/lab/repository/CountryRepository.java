package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
