package org.example.gestionchampionnatapi.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Entity
public class Championship {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message= "Le champs name ne peut être null")
    @NotBlank(message = "le champs name ne peut pas être vide")
    private String name;

    @NotNull(message= "Le champs startDate ne peut être null")
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern="yyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message= "Le champs endDate ne peut être null")
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern="yyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "Le champ wonPoint ne peut pas être null")
    private Long wonPoint;

    @NotNull(message = "Le champ lostPoint ne peut pas être null")
    private Long lostPoint;

    @NotNull(message = "Le champ wonPoint ne peut pas être null")
    private Long drawPoint;

    public Championship(){
    }

    public Championship(Long id, String name, LocalDate startDate, LocalDate endDate, Long wonPoint, Long lostPoint, Long drawPoint) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wonPoint = wonPoint;
        this.lostPoint = lostPoint;
        this.drawPoint = drawPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getWonPoint() {
        return wonPoint;
    }

    public void setWonPoint(Long wonPoint) {
        this.wonPoint = wonPoint;
    }

    public Long getLostPoint() {
        return lostPoint;
    }

    public void setLostPoint(Long lostPoint) {
        this.lostPoint = lostPoint;
    }

    public Long getDrawPoint() {
        return drawPoint;
    }

    public void setDrawPoint(Long drawPoint) {
        this.drawPoint = drawPoint;
    }
}
