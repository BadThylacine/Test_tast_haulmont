package com.test.haulmont.model;

import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;


    @OneToOne(mappedBy = "patient",optional = true)
    private Recipe recipe;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
