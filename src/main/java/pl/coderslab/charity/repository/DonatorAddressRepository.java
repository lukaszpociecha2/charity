package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.DonatorAddress;

public interface DonatorAddressRepository extends JpaRepository<DonatorAddress, Long> {


}
