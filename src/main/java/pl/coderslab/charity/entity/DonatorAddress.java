package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class DonatorAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    String cityAddress;
    @NotNull
    @NotEmpty
    @NotBlank
    String city;
    @NotNull
    @NotEmpty
    @NotBlank
    String postcode;
    @NotNull
    @NotEmpty
    @NotBlank
    String phone;

}
