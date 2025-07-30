package com.matheusmendes.rastreamento.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private VehicleState state;

    private LocalDateTime updateTime;

    private LocalDateTime estimatedArrivalTime;

    @Embedded
    private Car car;

    @Embedded
    private Route route;


    public Long getId() { return id; }

    public String getName() { return name; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public VehicleState getState() { return state; }
    public void setState(VehicleState state) { this.state = state; }

    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }

    public LocalDateTime getEstimatedArrivalTime() { return estimatedArrivalTime; }
    public void setEstimatedArrivalTime(LocalDateTime estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public Car getCar() { return car; }

    public Route getRoute() { return route; }
}
