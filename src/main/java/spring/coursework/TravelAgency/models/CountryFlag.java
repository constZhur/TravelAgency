package spring.coursework.TravelAgency.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Flag")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "file")
    private String fileName;
    @Column(name = "size")
    private Long size;
    @Column(name = "type")
    private String contentType;
    @Lob
    private byte[] bytes;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Country country;
}

