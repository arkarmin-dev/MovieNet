package cgm.system.MovieNet.service;

import cgm.system.MovieNet.entity.User;


public interface UserService {

    public void saveUser(User user);

    public void updatePassword(User user, String newPassword) ;

    public User getUserByUserName(String name) ;

}
