package com.matheusmendes.rastreamento.repository;

import com.matheusmendes.rastreamento.model.Vehicle;
import com.matheusmendes.rastreamento.model.VehicleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByState(VehicleState state);

    List<Vehicle> findByEstimatedArrivalTimeBefore(LocalDateTime limite);
}
