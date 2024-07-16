package cgm.system.MovieNet.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class PasswordResetForm {
    @NotNull(message = "Password Filed must be filled")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String newPassword;

    @NotNull(message = "Password Filed must be filled")
    private String confirmPassword;
}
