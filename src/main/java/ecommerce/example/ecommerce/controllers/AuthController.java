package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dto.AuthRequest;
import ecommerce.example.ecommerce.dto.AuthResponse;
import ecommerce.example.ecommerce.exception.AlreadyExistsException;
import ecommerce.example.ecommerce.model.User;
import ecommerce.example.ecommerce.request.CreateUserRequest;
import ecommerce.example.ecommerce.security.JwtService;
import ecommerce.example.ecommerce.security.UserDetailsImpl;
import ecommerce.example.ecommerce.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse("Login successful", token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody CreateUserRequest request) {
        try {
            User newUser = userService.createUser(request);
            String token = jwtService.generateToken(newUser);
            return ResponseEntity.ok(new AuthResponse("Registration successful", token));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout() {
        return ResponseEntity.ok(new AuthResponse("Logout successful", null));
    }
}
