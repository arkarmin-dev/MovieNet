package cgm.system.MovieNet.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginForm {
    @NotNull(message = "Fill the email")
    private String email;

    @NotNull(message = "Fill the password")
    private String password;
}
