package com.example.smartsoft.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class ValCurs {
    @Id
    private String name;
    private LocalDate date;

    @ToString.Exclude
    @OneToMany(mappedBy = "cursDate", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Valute> valuteList;

}
