package cz.patyk.invoicesystem_be.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    private Date lastLogin;
    private Date created;
    private Date passwordChanged;

    @Enumerated(EnumType.STRING)
    private Role roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
