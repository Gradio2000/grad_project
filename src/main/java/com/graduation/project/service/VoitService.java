package com.graduation.project.service;

import com.graduation.project.model.Restaurant;
import com.graduation.project.model.Voit;
import com.graduation.project.repository.RestRepository;
import com.graduation.project.repository.VoitRepository;
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
    public List<Restaurant> getResult() {
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

        return restRepository.findAllById(set);

    }
}
