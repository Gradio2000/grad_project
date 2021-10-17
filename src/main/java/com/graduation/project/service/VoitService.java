package com.graduation.project.service;

import com.graduation.project.model.Restaurant;
import com.graduation.project.model.Voit;
import com.graduation.project.repository.RestRepository;
import com.graduation.project.repository.VoitRepository;
import com.graduation.project.util.AuthUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.max;

@Service
public class VoitService {
    private final VoitRepository voitRepository;
    private final RestRepository restRepository;

    public VoitService(VoitRepository voitRepository, RestRepository restRepository) {
        this.voitRepository = voitRepository;
        this.restRepository = restRepository;
    }

    /*  сервис подсчета голосов */
    public ResponseEntity<List<Restaurant>> getResult() {
        //Время старта (сегодня начало дня)
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.of(0, 0);

        //Время финиша (сегодня конец дня)
        LocalDateTime start = LocalDateTime.of(localDate,localTime);
        LocalDateTime finish = start.plusMinutes(1439).plusSeconds(59);

        //Список всех голосов за сегодня
        List<Voit> voitList = voitRepository.findAllByToday(start, finish);

        //подсчет голосов и возврат списка ресторанов с максимальным количеством голосов
        Map<Integer, Integer> map = new HashMap<>();
        voitList.forEach(voit -> map.merge(voit.getRestId(), 1, (old, one) -> old + 1));
        int max = max(map.values());

        Set<Integer> set = map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), max))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return new ResponseEntity<>(restRepository.findAllById(set), HttpStatus.OK);

    }


    //сервис записи голоса в БД
    public ResponseEntity<Voit> addVoit(Voit voit, AuthUser authUser) {
        //проверяем, голосовал ли пользователь сегодня?
        LocalDateTime dateStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        LocalDateTime dateFinish = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        LocalTime localTime = LocalTime.of(11, 0);

        Voit voit1 = voitRepository.findByLocalDate(dateStart, dateFinish, authUser.getUser().getUserId());

        //если не голосовал, то...
        if (voit1 == null){
            voit.setUserId(authUser.getUser().getUserId());
            voit.setLocalDateTime(LocalDateTime.now());
            voitRepository.save(voit);
            return new ResponseEntity<>(voit, HttpStatus.CREATED);
        }
        //если уже сегодня проголосовал до 11.00, то изменяем голос
        if (voit1.getLocalDateTime().toLocalTime().isBefore(localTime)){
            LocalDateTime localDateTime = LocalDateTime.now();
            int voitId = voit1.getVoitId();
            int restId = voit.getRestId();
            voitRepository.update(voitId, localDateTime, restId);
            return new ResponseEntity<>(voit, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.SEE_OTHER);
    }
}
