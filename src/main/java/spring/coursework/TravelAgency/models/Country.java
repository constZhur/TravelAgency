package spring.coursework.TravelAgency.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Country")
@NoArgsConstructor
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Имя не должно быть пустым!")
    @Size(min = 3, max = 50, message = "Имя должно быть диапозоне от 3 до 50 символов! ")
    private String name;

    @Column(name = "description")
    @Size(min = 20, max = 500, message = "Описание должно быть диапозоне от 20 до 500 символов! ")
    private String description;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    public List<Tour> tours;

    public void addTour(Tour tour){
        if (tours == null) this.tours = new ArrayList<>();
        this.tours.add(tour);
        tour.setOwner(this);
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flag_id")
    private CountryFlag flag;

    public Country(String name){
        this.name = name;
    }
}
