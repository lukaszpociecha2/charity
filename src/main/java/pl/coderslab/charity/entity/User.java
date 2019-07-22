package pl.coderslab.charity.entity;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    String firstName;

    @NotNull
    @NotEmpty
    @NotBlank
    String lastName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    String username;

    @NotNull
    @NotEmpty
    @NotBlank
    String password;
    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
