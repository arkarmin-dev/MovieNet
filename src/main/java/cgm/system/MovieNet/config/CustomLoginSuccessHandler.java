package cgm.system.MovieNet.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = null;

        Collection<? extends GrantedAuthority> authorites = authentication.getAuthorities();
        for(GrantedAuthority grantedAuthority: authorites){
            String authorityName = grantedAuthority.getAuthority();
            if(authorityName.equals("Admin")){
                redirectUrl = "/admin/home";
                break;
            } else if (authorityName.equals("User")) {
                redirectUrl = "/user/home";
                break;
            }
        }
        if (redirectUrl == null) {
            throw new IllegalStateException();
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginSuccess", true);

        Cookie successCookie = new Cookie("loginSuccess", "false");
        successCookie.setMaxAge(1000);
        response.addCookie(successCookie);

        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
