package pl.coderslab.charity.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterRequest {

    String username;
    String firstName;
    String lastName;
    @NotEmpty
            @NotNull
            @NotBlank
    String password;
}
