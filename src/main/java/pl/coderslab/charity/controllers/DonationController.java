package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Organization;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.OrganizationRepository;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;

import javax.validation.Valid;
import java.util.List;


@Controller
public class DonationController {


    private DonationService donationService;
    private OrganizationRepository organizationRepository;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @ModelAttribute("organizationsList")
    private List<Organization> organizations(){
        return organizationRepository.findAll();
    }


    @Autowired
    public DonationController(DonationService donationService, OrganizationRepository organizationRepository, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.donationService = donationService;
        this.organizationRepository = organizationRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/donate")
    public String getForm(Model model){
        model.addAttribute("donation", new Donation());
        //model.addAttribute("organizationsList", organizations());
        model.addAttribute("categoriesTypes", categoryService.getAllCategories());
        return "form";
    }

    @PostMapping(value = "/donate") //TODO validation
    public String processFormModel(@Valid @ModelAttribute Donation donation, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            for (ObjectError objectError : errorList) {
                System.out.println(objectError.toString());
            }
            return "redirect:/form-confirmation";
        }

        //Donation donation = donationService.createDonationFromPayload(donationRequest);

        System.out.println(donation.toString());

        donationService.saveDonation(donation);

        return "redirect:/";
    }

}
