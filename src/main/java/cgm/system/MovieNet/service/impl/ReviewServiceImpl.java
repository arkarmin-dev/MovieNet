package cgm.system.MovieNet.service.impl;

import cgm.system.MovieNet.entity.Review;
import cgm.system.MovieNet.entity.User;
import cgm.system.MovieNet.repository.MovieRepository;
import cgm.system.MovieNet.repository.ReviewRepository;
import cgm.system.MovieNet.repository.UserRepository;
import cgm.system.MovieNet.service.MovieService;
import cgm.system.MovieNet.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void saveReview(Review review,String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User with username " + userName + " not found.");
        }


        review.setUser(user);


        reviewRepository.save(review);
    }
}

