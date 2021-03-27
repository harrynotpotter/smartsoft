package com.example.smartsoft.services;

import com.example.smartsoft.http.ValuteClientHttp;
import com.example.smartsoft.mappers.ValuteDTOMapper;
import com.example.smartsoft.models.dto.FromToDto;
import com.example.smartsoft.models.dto.ValCursDto;
import com.example.smartsoft.models.entity.History;
import com.example.smartsoft.models.entity.ValCurs;
import com.example.smartsoft.models.entity.Valute;
import com.example.smartsoft.repositories.HistoryRepository;
import com.example.smartsoft.repositories.ValCursRepository;
import com.example.smartsoft.repositories.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class ValuteService {

    @Autowired
    private ValuteRepository valuteRepository;
    @Autowired
    private ValCursRepository valCursRepository;
    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    private ValuteClientHttp valuteClientHttp;
    @Autowired
    private ValuteDTOMapper valuteDTOMapper;

    @Autowired
    public void setHistoryRepository(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Autowired
    public void setValuteRepository(ValuteRepository valuteRepository) {
        this.valuteRepository = valuteRepository;
    }

    public void saveValCurs (ValCurs valCurs) {
        valCursRepository.save(valCurs);
    }

    public List<Valute> getFreshValuteList() {
        Optional<LocalDate> lastUpdateDate = this.getValCursDate("Foreign Currency Market");
        if (!lastUpdateDate.isPresent() || lastUpdateDate.get().isBefore(LocalDate.now().plusDays(1))) {
            ValCursDto valCursDto = valuteClientHttp.getValCurs();
            final ValCurs valCurs = valuteDTOMapper.valCursDtoToEntity(valCursDto);
            valCurs.getValuteList().forEach(e -> e.setCursDate(valCurs));
            saveValCurs(valCurs);
            return valCurs.getValuteList();
        }
        return getAllValute();
    }

    public List<Valute> getAllValute() {
        List<Valute> valuteList = valuteRepository.findAll();
        if (CollectionUtils.isEmpty(valuteList)) {
            valuteList = getFreshValuteList();
        }
        return valuteList;
    }


    public void saveHistory(FromToDto fromToDto) {
        History history = new History();

        final Optional<Valute> from = valuteRepository.findById(fromToDto.getFrom());
        final Optional<Valute> to = valuteRepository.findById(fromToDto.getTo());
        if (from.isPresent() && to.isPresent()) {
            Valute valuteFrom = from.get();
            final Valute valuteTo = to.get();

            history.setFromValute(valuteFrom.getCharCode() + " (" + valuteFrom.getName() + ")");
            history.setToValute(valuteTo.getCharCode() + " (" + valuteTo.getName() + ")");
            history.setFromValue(fromToDto.getFromQ());
            history.setToValue(fromToDto.getToQ());

            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            history.setDate(formatter.format(new Date()));
            historyRepository.save(history);
        }
    }

    public FromToDto convertValue(FromToDto fromToDto) {
        BigDecimal crossCourse = getCrossCourse(fromToDto.getFrom(), fromToDto.getTo());
        if (fromToDto.getFromQ().isBlank()) {
            fromToDto.setFromQ("1");
        }
        BigDecimal toQ = crossCourse.multiply(new BigDecimal(fromToDto.getFromQ()));
        fromToDto.setToQ(String.valueOf(toQ));
        return fromToDto;
    }

    private BigDecimal getCrossCourse(String fromId, String toId) {
        Optional<Valute> fromValute = valuteRepository.findById(fromId);
        Optional<Valute> toValute = valuteRepository.findById(toId);
        BigDecimal f = fromValute.get().getValue();
        BigDecimal t = toValute.get().getValue();
        BigDecimal d = f.divide(t, 2, RoundingMode.CEILING);
        return d;
    }

    public Optional<LocalDate> getValCursDate(String id){
        return valCursRepository.findById(id)
                .map(ValCurs::getDate);
    }

    public void save(Valute valute) {
        valuteRepository.save(valute);
    }


    public List<History> getHistory() {
        return historyRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }
}
