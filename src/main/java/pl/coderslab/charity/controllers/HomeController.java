package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.repository.OrganizationRepository;
import pl.coderslab.charity.service.DonationService;


@Controller
public class HomeController {

    private DonationService donationService;
    private OrganizationRepository organizationRepository;

    public HomeController(DonationService donationService, OrganizationRepository organizationRepository) {
        this.donationService = donationService;
        this.organizationRepository = organizationRepository;
    }

    @GetMapping("/")
    public String homeAction(Model model){
        model.addAttribute("organizations", organizationRepository.findAll());
        model.addAttribute("supportedInstitutions", donationService.numberOfOrganizationsWithDontaions());
        model.addAttribute("numberOfBags", donationService.numberOfBags());
        return "index";
    }



}
