package at.biglmatthias.WeLift.UserRegistration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor

public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void save(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationToken findByToken(String token) {
        AtomicReference<ConfirmationToken> confirmationToken = new AtomicReference<>();
        confirmationTokenRepository.findByToken(token).ifPresent(
                c->{
                    if(c.isExpired()){
                        throw new IllegalArgumentException("Token is expired");
                    }
                    confirmationToken.set(c);
                }


        );
        return confirmationToken.get();
    }
}
