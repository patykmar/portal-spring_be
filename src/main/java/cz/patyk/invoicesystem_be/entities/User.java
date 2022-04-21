package cz.patyk.invoicesystem_be.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    public enum Role {ADMIN, USER}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "employee_of_company_id")
    @ToString.Exclude
    private Company employeeOfCompanyId;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long lastLogin;
    private Long created;
    private Long passwordChanged;

    @Enumerated(EnumType.STRING)
    private Role roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(employeeOfCompanyId, user.employeeOfCompanyId) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(lastLogin, user.lastLogin) && Objects.equals(created, user.created) && Objects.equals(passwordChanged, user.passwordChanged) && roles == user.roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeOfCompanyId, email, password, firstName, lastName, lastLogin, created, passwordChanged, roles);
    }
}
