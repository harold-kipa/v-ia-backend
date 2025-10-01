package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.v_ia_backend.kipa.config.JwtResponse;
import com.v_ia_backend.kipa.config.JwtTokenUtil;
import com.v_ia_backend.kipa.dto.request.LoginRequest;
import com.v_ia_backend.kipa.entity.Users;
import com.v_ia_backend.kipa.exception.listexceptions.BadRequestException;
import com.v_ia_backend.kipa.exception.listexceptions.NotFoundException;
import com.v_ia_backend.kipa.modelview.UserView;
import com.v_ia_backend.kipa.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Value("${INVALID_CREDENTIALS}")
    private String invalidCredentials;

    @Value("${USER_DISABLED}")
    private String userDisable;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserServiceImpl userServiceImpl;
    public AuthController(AuthenticationManager authenticationManager, UserServiceImpl userServiceImpl, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userServiceImpl = userServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest userLogin) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
        } catch (DisabledException e) {
            throw new NotFoundException(userDisable);
        } catch (BadCredentialsException e) {
            throw new BadRequestException(invalidCredentials);
        }
        Users userDB = userServiceImpl.getUserByEmail(userLogin.getEmail());
        UserView userView = new UserView(userDB);

        final UserDetails userDetails = userServiceImpl.loadUserByUsername(userLogin.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userView));
    }
    // @PostMapping("/register")
    // public ResponseEntity<Object> register() {
    //     return "register successful";
    // }
    
}
