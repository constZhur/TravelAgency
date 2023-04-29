package spring.coursework.TravelAgency.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @JsonIgnore
    private Country owner;

    @ManyToMany(mappedBy = "tours")
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
