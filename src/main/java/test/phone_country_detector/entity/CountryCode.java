package test.phone_country_detector.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity для хранения названия стран с кодов сотовых операторов.
 */

@Entity
@Table(name = "country_codes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String code;

    @ElementCollection
    @CollectionTable(name = "additional_codes", joinColumns = @JoinColumn(name = "country_code_id"))
    @Column(name = "code")
    private List<String> additionalCodes;
}