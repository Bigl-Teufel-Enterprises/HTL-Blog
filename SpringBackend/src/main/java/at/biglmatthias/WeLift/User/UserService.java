package at.biglmatthias.WeLift.User;


import at.biglmatthias.WeLift.UserRegistration.token.ConfirmationToken;
import at.biglmatthias.WeLift.UserRegistration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final String USER_NOT_FOUND = "%s not found";

    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
    }

    public String signUpUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("Username already exists");
        });
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Email already exists");
        });
        String encodedPw = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPw);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confimationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user

        );
        confirmationTokenService.save(confimationToken);

        //TODO: send email with token

        return token;
    }
    public String confirm(String token) {
        ConfirmationToken confimationToken = confirmationTokenService.findByToken(token);
        if (confimationToken == null) {
            return "Token not found";
        }
        if (confimationToken.isExpired()) {
            return "Token is expired";
        }
        User user = confimationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return "User confirmed";
    }

}
