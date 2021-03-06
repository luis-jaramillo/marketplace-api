package dev.lfjaramillos.marketplace.demo.web.controller;

import dev.lfjaramillos.marketplace.demo.domain.dto.AuthenticationRequest;
import dev.lfjaramillos.marketplace.demo.domain.dto.AuthenticationResponse;
import dev.lfjaramillos.marketplace.demo.domain.service.DemoMarketUserDetailService;
import dev.lfjaramillos.marketplace.demo.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager  authenticationManager;

    @Autowired
   private UserDetailsService userDetailsService;
    @Autowired
   private DemoMarketUserDetailService demoMarketUserDetailService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
            String jWt = jwtUtil.generateToken(userDetails);
            return new  ResponseEntity<>( new AuthenticationResponse(jWt), HttpStatus.OK);
        }catch (BadCredentialsException badCredentialsException){
            return new  ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
