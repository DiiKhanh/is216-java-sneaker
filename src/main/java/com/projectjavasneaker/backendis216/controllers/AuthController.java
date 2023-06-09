package com.projectjavasneaker.backendis216.controllers;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.projectjavasneaker.backendis216.models.Cart;
import com.projectjavasneaker.backendis216.models.Role;
import com.projectjavasneaker.backendis216.models.User;
import com.projectjavasneaker.backendis216.payload.request.LoginRequest;
import com.projectjavasneaker.backendis216.payload.request.SignupRequest;
import com.projectjavasneaker.backendis216.payload.response.JwtResponse;
import com.projectjavasneaker.backendis216.payload.response.MessageResponse;
import com.projectjavasneaker.backendis216.models.ERole;
import com.projectjavasneaker.backendis216.payload.response.ResponseObject;
import com.projectjavasneaker.backendis216.repository.CartRepository;
import com.projectjavasneaker.backendis216.repository.RoleRepository;
import com.projectjavasneaker.backendis216.repository.UserRepository;
import com.projectjavasneaker.backendis216.security.jwt.JwtUtils;
import com.projectjavasneaker.backendis216.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    CartRepository cartRepository;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

       if(userDetails.getCart() == null){
           return ResponseEntity.ok(new JwtResponse(jwt,
                   userDetails.getId(),
                   userDetails.getUsername(),
                   userDetails.getEmail(),
                   roles,
                   userDetails.getAddress(),
                   userDetails.getPhone(),
                   userDetails.getGender(),
                   userDetails.getBirth()
           ));
       }

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                userDetails.getAddress(),
                userDetails.getPhone(),
                userDetails.getGender(),
                userDetails.getBirth(),
                userDetails.getCart().getCartId()
        ));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        if (userRepository.existsByPhone(signUpRequest.getPhone())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Phone number is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getAddress(),signUpRequest.getPhone(),
                signUpRequest.getGender(),signUpRequest.getBirth(), signUpRequest.getRole().toString()
                );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        Cart cart = new Cart();
        //user.setCart(cart);
        cart.setUser(user);

        cartRepository.save(cart);

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseObject> logOut(){
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseObject("ok", "logout successfully!", null)
        );
    }

}
