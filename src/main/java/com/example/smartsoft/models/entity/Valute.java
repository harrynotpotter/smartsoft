package com.example.smartsoft.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Valute {
    @Id
    private String id;
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private BigDecimal value;
    @ManyToOne
    @JoinColumn(name = "curs_date", nullable = false)
    private ValCurs cursDate;
}
