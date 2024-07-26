package cgm.system.MovieNet.service;

import cgm.system.MovieNet.entity.User;


public interface EmailSendService {

    public void sendMail(User user, String email);
}
