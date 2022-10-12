package at.biglmatthias.WeLift.UserRegistration;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String username;
    private final String email;
    private final String password;
    private final String bio;
    private final String profilePicture;


}
