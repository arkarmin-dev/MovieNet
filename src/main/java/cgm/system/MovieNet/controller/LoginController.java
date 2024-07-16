package cgm.system.MovieNet.controller;


import cgm.system.MovieNet.entity.PasswordReset;
import cgm.system.MovieNet.entity.Role;
import cgm.system.MovieNet.entity.User;
import cgm.system.MovieNet.form.PasswordResetForm;
import cgm.system.MovieNet.form.UserForm;
import cgm.system.MovieNet.repository.RoleRepository;
import cgm.system.MovieNet.repository.UserRepository;
import cgm.system.MovieNet.service.EmailSendService;
import cgm.system.MovieNet.service.PasswordResetService;
import cgm.system.MovieNet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmailSendService service;

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value ="/sign_up", method = RequestMethod.GET)
    public ModelAndView goToSignUp(){
        ModelAndView model = new ModelAndView("signup");
        UserForm user = new UserForm();
        model.addObject("user",user);
        return model;
    }

    @PostMapping(value="/save")
    public ModelAndView saveUserList(@ModelAttribute("user") UserForm user){
        ModelAndView view = new ModelAndView("login");
        Role role = new Role("User");
        roleRepository.save(role);
        User userAdd = new User(user.getUserName(),user.getEmail(),passwordEncoder.encode(user.getPassword()),role);
        userRepository.save(userAdd);
        return view;
    }

    @GetMapping("/forget_password")
    public String forgetPassword(){
        return "forgetPassword";
    }

    @RequestMapping(value = "/emailSend", method = RequestMethod.POST)
    public String sendEmail(@RequestParam("email") String email) {
        System.out.println(email);
        User user = userRepository.findByEmail(email);
        if(user == null){
            return "forgetPassword";
        } else {
            service.sendMail(user, email);
            return "login";
        }
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.GET)
    public String resetPassword(@RequestParam("token") String token, Model model){
        PasswordResetForm form = new PasswordResetForm();
        model.addAttribute("form", form);
        model.addAttribute("token", token);
        return "resetPassword";
    }

    @PostMapping("/password_reset")
    public String resetPassword(@RequestParam("token") String token,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword) {
        // Validate token
        PasswordReset resetToken = passwordResetService.findByToken(token);

        if (resetToken == null || !resetToken.isValid()) {
            // Handle invalid token
            return "forgetPassword";
        }

        // Validate passwords match
        if (!newPassword.equals(confirmPassword)) {
            // Handle passwords not matching
            return "resetPassword";
        }

        // Update password
        User user = resetToken.getUser();
        userService.updatePassword(user, newPassword);

        // Invalidate the token
        passwordResetService.invalidateToken(resetToken);

        return "login";
    }

}
