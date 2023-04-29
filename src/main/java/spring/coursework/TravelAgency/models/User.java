package spring.coursework.TravelAgency.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Person")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    @Email(message = "Email is invalid!")
    @NotEmpty(message = "Email cannot be empty!")
    private String email;

    @Column(name = "password")
    @Size(min = 8, message = "Password must contain more than 8 characters!")
    @NotEmpty(message = "Password cannot be empty!")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "person_tour",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id")
    )
    List<Tour> tours;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
