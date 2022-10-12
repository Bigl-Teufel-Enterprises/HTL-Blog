package at.biglmatthias.WeLift.UserRegistration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/registration")
@AllArgsConstructor
public class UserRegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest request) {
        return registrationService.registerUser(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirm(token);
    }

}
