package spring.coursework.TravelAgency.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
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
    @Email(message = "Некорректный ввод емаила!")
    @NotEmpty(message = "Емаил не может быть пустым!")
    private String email;

    @Column(name = "password")
    @Size(min = 8, message = "Пароль должен состоять не менее из 8 символов!")
    @NotEmpty(message = "Пароль не может быть пустым!")
    private String password;

    @Column(name = "role")
    private String role;

    @ManyToMany
    @JoinTable(
            name = "person_tour",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<Tour> tours = new ArrayList<>();

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

//    public void deleteTour(Tour tour){
//        tours.remove(tour);
//    }
}
