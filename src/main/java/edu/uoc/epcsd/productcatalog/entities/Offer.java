package edu.uoc.epcsd.productcatalog.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @Column(name = "serialNumber", nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "dailyPrice", nullable = false)
    private Double dailyPrice;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "status", nullable = false)
    private OfferStatus status;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate date;
}
