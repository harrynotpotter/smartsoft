package com.example.smartsoft.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.List;


@Getter
@XmlRootElement(name = "ValCurs")
public class ValCursDto {
    private String name;
    private String date;
    private List<ValuteDto> valute;

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "Date")
    public void setDate(String date) {
        this.date = date;
    }
    @XmlElement(name = "Valute")
    public void setValute(List<ValuteDto> valute) {
        this.valute = valute;
    }
}
