package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@Entity
public class PickupData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
            @NotEmpty
            @NotBlank
    String date;
    @NotNull
    @NotEmpty
    @NotBlank
    String time;

    String more_info;

    @ManyToOne(cascade = CascadeType.PERSIST)
            @Valid
    DonatorAddress donatorAddress;

}
