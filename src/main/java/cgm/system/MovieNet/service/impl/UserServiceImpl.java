package cgm.system.MovieNet.service.impl;

import cgm.system.MovieNet.entity.User;
import cgm.system.MovieNet.repository.UserRepository;
import cgm.system.MovieNet.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        System.out.println(newPassword);
    }

    public User getUserByUserName(String name) {
        return userRepository.findByName(name);
    }
}
