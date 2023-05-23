package com.projectjavasneaker.backendis216.security.oauth2;

import com.projectjavasneaker.backendis216.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DatabaseLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    UserService userService;

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws ServletException, IOException {
//        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
//        userService.updateAuthenticationType(userDetails.getUsername(), "database");
//        super.onAuthenticationSuccess(request, response, authentication);
//    }

}