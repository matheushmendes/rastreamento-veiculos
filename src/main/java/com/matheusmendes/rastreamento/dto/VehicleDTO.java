package com.matheusmendes.rastreamento.dto;

import com.matheusmendes.rastreamento.model.VehicleState;
import java.time.LocalDateTime;

public class VehicleDTO {

    public Long id;
    public String name;
    public String placa;
    public String modelo;
    public String color;
    public Double latitude;
    public Double longitude;
    public VehicleState state;
    public LocalDateTime estimatedArrivalTime;

}
