package mar.io.htlblog.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Table(name = "user")
@Entity
@Data
public class User implements UserDetails {

    // Account information
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long accID;
    @Getter(AccessLevel.NONE) // Prevent Lombok from generating a getter since there already is one
    private boolean accIsEnabled;
    @Getter(AccessLevel.NONE) // no getter
    private boolean accIsLocked;

    // Personal information
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    // User information
    @Getter(AccessLevel.NONE) // no getter
    private String userName;
    @Getter(AccessLevel.NONE) // no getter
    private String userPassword;
    private String userEmail;
    private String userBio;
    private String userPicUrl;
    // Store roles as String, so the order of the enum doesn't compromise persistence
    // In return, the names can't be changed
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    public boolean isAccountLocked() {
        return !accIsLocked;
    }

    @Override
    public boolean isEnabled() {
        return accIsEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accID); // Account ID should be unique
    }

    @Override
    public String toString() {
        return "User{" +
                "accID=" + accID +
                ", birthDate=" + birthDate +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
