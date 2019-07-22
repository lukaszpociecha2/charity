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


    @Autowired
    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }


    public Donation saveDonation(Donation donation){
        return donationRepository.save(donation);
    }

    public Integer numberOfOrganizationsWithDontaions(){
        return donationRepository.countSupportedInstitutions();
    }

    public Integer numberOfBags(){
        return donationRepository.sumOfBags();
    }


}
