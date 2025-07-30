package com.matheusmendes.rastreamento.repository;

import com.matheusmendes.rastreamento.model.Vehicle;
import com.matheusmendes.rastreamento.model.VehicleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // 🔍 Filtra os veículos por estado (EM_ROTA, CONCLUIDO, etc.)
    List<Vehicle> findByState(VehicleState state);

    // ⏱️ Retorna veículos com tempo estimado de chegada antes de determinado horário
    List<Vehicle> findByEstimatedArrivalTimeBefore(LocalDateTime limite);
}
