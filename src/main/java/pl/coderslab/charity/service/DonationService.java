package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.*;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.OrganizationRepository;

@Service
public class DonationService {

    private DonationRepository donationRepository;
    private OrganizationRepository organizationRepository;
    private CategoryRepository categoryRepository;


    @Autowired
    public DonationService(DonationRepository donationRepository, OrganizationRepository organizationRepository, CategoryRepository categoryRepository) {
        this.donationRepository = donationRepository;
        this.organizationRepository = organizationRepository;
        this.categoryRepository = categoryRepository;
    }


    public Donation saveDonation(Donation donation){
        return donationRepository.save(donation);
    }

}
