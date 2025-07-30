package com.matheusmendes.rastreamento.service;

import com.matheusmendes.rastreamento.dto.VehicleDTO;
import com.matheusmendes.rastreamento.model.Vehicle;
import com.matheusmendes.rastreamento.model.VehicleState;
import com.matheusmendes.rastreamento.repository.VehicleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle findById(long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }


    public List<Vehicle> buscarPorEstado(VehicleState estado) {
        return vehicleRepository.findByState(estado);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> buscarPorEtaAntesDe(LocalDateTime limite) {
        return vehicleRepository.findByEstimatedArrivalTimeBefore(limite);
    }

    public VehicleDTO converterParaDTO(Vehicle v) {
        VehicleDTO dto = new VehicleDTO();
        dto.id = v.getId();
        dto.name = v.getName();
        dto.latitude = v.getLatitude();
        dto.longitude = v.getLongitude();
        dto.state = v.getState();
        dto.estimatedArrivalTime = v.getEstimatedArrivalTime();

        if (v.getCar() != null) {
            dto.placa = v.getCar().getPlaca();
            dto.modelo = v.getCar().getModelo();
            dto.color = v.getCar().getColor();
        }

        return dto;
    }

}
