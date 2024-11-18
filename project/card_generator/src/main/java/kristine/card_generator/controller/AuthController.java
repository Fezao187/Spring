package kristine.card_generator.controller;

import kristine.card_generator.models.responses.AuthResponse;
import kristine.card_generator.models.entities.User;
import kristine.card_generator.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/get/token")
    public ResponseEntity<String> getToken(
            @RequestHeader(name="Authorization") String token
    ) {
        return ResponseEntity.ok(token);
    }
}
