package cgm.system.MovieNet.service;

import cgm.system.MovieNet.entity.PasswordReset;
import cgm.system.MovieNet.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface PasswordResetService {

    public PasswordReset findByToken(String token) ;

    public void createPasswordResetTokenForUser(User user, String token) ;

    public void invalidateToken(PasswordReset token) ;
}
