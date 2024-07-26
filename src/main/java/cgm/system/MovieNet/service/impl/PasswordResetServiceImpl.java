package cgm.system.MovieNet.service.impl;

import cgm.system.MovieNet.entity.PasswordReset;
import cgm.system.MovieNet.entity.User;
import cgm.system.MovieNet.repository.PasswordResetRepository;
import cgm.system.MovieNet.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private PasswordResetRepository tokenRepository;


    public PasswordReset findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void createPasswordResetTokenForUser(User user,String token) {
        PasswordReset myToken = new PasswordReset(user, token, LocalDateTime.now().plusHours(24));
        tokenRepository.save(myToken);
    }

    public void invalidateToken(PasswordReset token) {
        tokenRepository.delete(token);
    }
}
