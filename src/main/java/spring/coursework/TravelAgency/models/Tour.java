package spring.coursework.TravelAgency.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Tour")
@Getter
@Setter
@NoArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Имя не должно быть пустым!")
    @Size(min = 3, max = 50, message = "Имя должно быть диапозоне от 3 до 50 символов!")
    private String name;

    @Column(name = "price")
    @Min(value = 100, message = "Цена должна быть не менее 100!")
    private int price;

    @Column(name = "description")
    @NotEmpty(message = "Описание не должно быть пустым!")
    @Size(min = 10, max = 500, message = "Описание должно быть диапозоне от 10 до 500 символов!")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @JsonIgnore
    private Country owner;

    @ManyToMany(mappedBy = "tours", cascade = CascadeType.ALL)
    @JsonIgnore
    List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return id == tour.id && price == tour.price && Objects.equals(name, tour.name) && Objects.equals(description, tour.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description);
    }
}
