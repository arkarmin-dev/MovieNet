package cgm.system.MovieNet.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserForm {
    private Long id;

    @NotNull(message = "Fill the name")
    private String userName;

    @NotNull(message = "Fill the email")
    private String email;

    @NotNull(message = "Fill the password")
    private String password;

}
