package com.example.smartsoft.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@XmlRootElement(name = "Values")
public class ValuteDto {
    private String id;
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private String value;

    @XmlAttribute (name="ID")
    public void setId(String id) {
        this.id = id;
    }
    @XmlElement(name = "NumCode")
    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }
    @XmlElement(name = "CharCode")
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }
    @XmlElement(name = "Nominal")
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "Value")
    public void setValue(String value) {
        this.value = value;
    }
}
