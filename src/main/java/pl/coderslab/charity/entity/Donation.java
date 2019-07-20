package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToMany(cascade = CascadeType.PERSIST)
            @NotEmpty
    List<Category> categories = new ArrayList<>();

    @NotNull
    @Min(1)
    Integer bags;

    @ManyToOne(cascade = CascadeType.PERSIST)
            @NotNull
    Organization recepient;

    @OneToOne(cascade = CascadeType.PERSIST)
            @NotNull
            @Valid
    PickupData pickupData;

}
