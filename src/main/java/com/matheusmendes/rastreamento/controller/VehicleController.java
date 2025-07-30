package com.matheusmendes.rastreamento.controller;

import com.matheusmendes.rastreamento.dto.VehicleDTO;
import com.matheusmendes.rastreamento.model.Vehicle;
import com.matheusmendes.rastreamento.model.VehicleState;
import com.matheusmendes.rastreamento.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @GetMapping
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.findAll().stream()
                .map(vehicleService::converterParaDTO)
                .toList();
    }

    @PostMapping
    public ResponseEntity<Vehicle> criarVeiculo(@RequestBody Vehicle vehicle) {
        Vehicle salvo = vehicleService.save(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }


    @GetMapping("/em-rota")
    public List<VehicleDTO> getVehiclesEmRota() {
        return vehicleService.buscarPorEstado(VehicleState.EM_ROTA).stream()
                .map(vehicleService::converterParaDTO)
                .toList();
    }


    @GetMapping("/concluidos")
    public List<Vehicle> getVehiclesConcluidos() {
        return vehicleService.buscarPorEstado(VehicleState.CONCLUIDO);
    }

    @GetMapping("/chegando-em/{minutos}")
    public List<Vehicle> getVehiclesComEtaMenor(@PathVariable long minutos) {
        LocalDateTime limite = LocalDateTime.now().plusMinutes(minutos);
        return vehicleService.buscarPorEtaAntesDe(limite);
    }
}
