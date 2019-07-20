package pl.coderslab.charity.converter;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.entity.Organization;
import pl.coderslab.charity.repository.OrganizationRepository;

public class OrganizationConverter implements Converter<String, Organization> {

    private OrganizationRepository organizationRepository;

    public OrganizationConverter(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization convert(String s) {
        return organizationRepository.findByName(s);
    }


}
