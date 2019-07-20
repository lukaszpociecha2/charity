package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByName(String name);

}
