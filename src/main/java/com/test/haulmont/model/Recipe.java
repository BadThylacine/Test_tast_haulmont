package com.test.haulmont.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.haulmont.RecipePriority;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn (name = "doctor_id")
    private Doctor doctor;

    private String manufacture;
    private String duration;



    @Enumerated(EnumType.ORDINAL)
    private RecipePriority priority;

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", duration='" + duration + '\'' +
                ", priority=" + priority +
                '}';
    }

}
