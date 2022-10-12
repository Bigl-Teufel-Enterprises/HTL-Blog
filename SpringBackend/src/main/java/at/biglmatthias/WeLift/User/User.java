package at.biglmatthias.WeLift.User;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Table(name = "users")
@Entity
@EqualsAndHashCode
@NoArgsConstructor

public class User implements UserDetails {
    // a class for the author of an article containing the id ,the username, the email, the bio,the Role and the profile picture if available
    // the author needs a password to be able to login the password is hashed and salted
    // the author needs a role to be able to login and to be able to create articles
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"

    )
    private long id;
    private String username;
    private String email;
    private String bio;
    private String profilePicture;
    private String password;
    private Boolean enabled=false;

    private Boolean locked=false;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public User(long id, String username, String email, String bio, String profilePicture, String password, Roles role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.password = password;
        this.role = role;

    }

    public User(String username, String email, String bio, String profilePicture, String password, Roles role) {
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.password = password;
        this.role = role;

    }

    public User(long id, String username, String email, String bio, String password, Roles role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.profilePicture = null;
        this.password = password;
        this.role = role;
    }

    public User(String username, String email, String bio, String password, Roles role) {
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.profilePicture = null;
        this.password = password;
        this.role = role;

    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }
    @Override
    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", bio=" + bio + ", profilePicture=" + profilePicture + ", password=" + password + ", role=" + role + '}';
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
