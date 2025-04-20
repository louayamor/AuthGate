package itbs.louayamor.authgate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itbs.louayamor.authgate.client.UserClient;
import itbs.louayamor.authgate.dto.AuthRequest;
import itbs.louayamor.authgate.dto.AuthResponse;
import itbs.louayamor.authgate.dto.UserDTO;
import itbs.louayamor.authgate.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserClient userClient;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            UserDTO user = userClient.verifyUser(request);
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
