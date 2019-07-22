package pl.coderslab.charity.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@Entity
public class PickupData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    @NotNull
    @NotEmpty
    @NotBlank
    String time;

    String more_info;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @Valid
    DonatorAddress donatorAddress;

}
