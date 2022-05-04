package cz.patyk.invoicesystem_be.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
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
    private Long createdDate;
    private Long passwordChanged;

    @Enumerated(EnumType.STRING)
    private Role roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeOfCompanyId, email, password, firstName, lastName, lastLogin, createdDate, passwordChanged, roles);
    }
}
