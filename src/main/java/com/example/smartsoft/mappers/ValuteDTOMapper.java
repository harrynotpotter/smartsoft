package com.example.smartsoft.mappers;

import com.example.smartsoft.models.dto.ValCursDto;
import com.example.smartsoft.models.dto.ValuteDto;
import com.example.smartsoft.models.entity.ValCurs;
import com.example.smartsoft.models.entity.Valute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ValuteDTOMapper {
    //ValCursDto entityToDto(ValCurs valCurs);
    @Mappings({
            @Mapping(target="name", source="valCursDto.name"),
            @Mapping(target="valuteList", source="valCursDto.valute"),
            @Mapping(target = "date", source = "date", qualifiedByName = "convertDate")
    })
    ValCurs valCursDtoToEntity (ValCursDto valCursDto);

    @Mappings({
            @Mapping(target="id", source="valuteDto.id"),
            @Mapping(target="numCode", source="valuteDto.numCode"),
            @Mapping(target="charCode", source="valuteDto.charCode"),
            @Mapping(target="nominal", source="valuteDto.nominal"),
            @Mapping(target="name", source="valuteDto.name"),
            @Mapping(target="value", source="valuteDto.value", qualifiedByName = "convertValue")
    })
    Valute valuteDtoToEntity (ValuteDto valuteDto);

    List<Valute> convert(List<ValuteDto>listDto);

    @Named("convertValue")
    default BigDecimal convertValue(String value) {
        String s = value.replaceAll(",", ".");

        return new BigDecimal(s);
    }

    @Named("convertDate")
    default LocalDate convertDate(String eventTime) {
        DateTimeFormatter custom = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(eventTime, custom);
    }
}
