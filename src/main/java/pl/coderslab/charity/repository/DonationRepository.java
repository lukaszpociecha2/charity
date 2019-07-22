package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT SUM(bags) from donation", nativeQuery = true)
    Integer sumOfBags();

    @Query(value = "select count(distinct recepient_id) from donation", nativeQuery = true)
    Integer countSupportedInstitutions();

}
