package com.LoopPop.LoopPop.AppUser;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// Mark this class as a JPA entity (a class that is mapped to a database table)
@Entity
// Specify the details of the database table that this entity will map to
@Table(name = "app_user")
public class AppUser implements UserDetails {

    // Configure the generation of unique IDs for instances of AppUser, using a database sequence
    @SequenceGenerator(
            name = "user_sequence",         // Name of the sequence generator
            sequenceName = "user_sequence", // Name of the database sequence
            allocationSize = 1              // Increment size for the sequence
    )
    // Specify the primary key of the entity
    @Id
    // Define the strategy for generating the primary key value
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // Use sequence-based ID generation
            generator = "user_sequence"         // Specify the sequence generator to use
    )
    private Long id;

    // Define fields to map to columns in the app_user table
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // Specify that the appUserRole field should be persisted as a String in the database
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    // Define fields for account status with default values
    private Boolean locked = false;   // Indicates if the account is locked
    private Boolean enabled = true;   // Indicates if the account is enabled

    // Constructor to initialize all fields except id
    public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    // Default constructor for JPA
    public AppUser() {
    }

    // Return the authorities granted to the user (required by UserDetails)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Create a SimpleGrantedAuthority object with the name of the user's role
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.appUserRole.name());
        // Return the authority as a collection
        return Collections.singletonList(authority);
    }

    // Getters for the fields (required by UserDetails and other purposes)
    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }

    // Return the username for authentication (in this case, the email)
    public String getUsername() {
        return this.email;
    }

    // Account non-expired status (always true in this case)
    public boolean isAccountNonExpired() {
        return true;
    }

    // Account non-locked status (true if the account is not locked)
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    // Credentials non-expired status (always true in this case)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Account enabled status
    public boolean isEnabled() {
        return this.enabled;
    }

    // Setter for the password
    public void setPassword(String password) {
        this.password = password;
    }

    // Overridden equals method to compare AppUser objects based on their fields
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AppUser)) {
            return false;
        } else {
            AppUser other = (AppUser)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label107: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label107;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label107;
                    }

                    return false;
                }

                Object this$locked = this.locked;
                Object other$locked = other.locked;
                if (this$locked == null) {
                    if (other$locked != null) {
                        return false;
                    }
                } else if (!this$locked.equals(other$locked)) {
                    return false;
                }

                Object this$enabled = this.enabled;
                Object other$enabled = other.enabled;
                if (this$enabled == null) {
                    if (other$enabled != null) {
                        return false;
                    }
                } else if (!this$enabled.equals(other$enabled)) {
                    return false;
                }

                label86: {
                    Object this$firstName = this.getFirstName();
                    Object other$firstName = other.getFirstName();
                    if (this$firstName == null) {
                        if (other$firstName == null) {
                            break label86;
                        }
                    } else if (this$firstName.equals(other$firstName)) {
                        break label86;
                    }

                    return false;
                }

                label79: {
                    Object this$lastName = this.getLastName();
                    Object other$lastName = other.getLastName();
                    if (this$lastName == null) {
                        if (other$lastName == null) {
                            break label79;
                        }
                    } else if (this$lastName.equals(other$lastName)) {
                        break label79;
                    }

                    return false;
                }

                label72: {
                    Object this$email = this.email;
                    Object other$email = other.email;
                    if (this$email == null) {
                        if (other$email == null) {
                            break label72;
                        }
                    } else if (this$email.equals(other$email)) {
                        break label72;
                    }

                    return false;
                }

                Object this$password = this.getPassword();
                Object other$password = other.getPassword();
                if (this$password == null) {
                    if (other$password != null) {
                        return false;
                    }
                } else if (!this$password.equals(other$password)) {
                    return false;
                }

                Object this$appUserRole = this.appUserRole;
                Object other$appUserRole = other.appUserRole;
                if (this$appUserRole == null) {
                    if (other$appUserRole != null) {
                        return false;
                    }
                } else if (!this$appUserRole.equals(other$appUserRole)) {
                    return false;
                }

                return true;
            }
        }
    }

    // Helper method used in the equals method to check if the other object can be equal to this one
    protected boolean canEqual(final Object other) {
        return other instanceof AppUser;
    }

    // Overridden hashCode method to generate a hash code based on the fields
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $locked = this.locked;
        result = result * 59 + ($locked == null ? 43 : $locked.hashCode());
        Object $enabled = this.enabled;
        result = result * 59 + ($enabled == null ? 43 : $enabled.hashCode());
        Object $firstName = this.getFirstName();
        result = result * 59 + ($firstName == null ? 43 : $firstName.hashCode());
        Object $lastName = this.getLastName();
        result = result * 59 + ($lastName == null ? 43 : $lastName.hashCode());
        Object $email = this.email;
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $appUserRole = this.appUserRole;
        result = result * 59 + ($appUserRole == null ? 43 : $appUserRole.hashCode());
        return result;
    }
}
