package com.matheusmendes.rastreamento.simulation;

import com.matheusmendes.rastreamento.model.Vehicle;
import com.matheusmendes.rastreamento.model.VehicleState;
import com.matheusmendes.rastreamento.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleSimulationService {

    /* SIMULADOR DE VEICULO SE MOVENDO COM LATITUDE E LONGITUDE*/

    @Autowired
    private VehicleRepository repository;

    @Scheduled(fixedRate = 1000)
    public void run() {
        List<Vehicle> inRoute = repository.findByState(VehicleState.EM_ROTA);

        for (Vehicle v : inRoute) {
            v.setLatitude(v.getLatitude() + Math.random() * 0.001);
            v.setLongitude(v.getLongitude() + Math.random() * 0.001);
            v.setUpdateTime(LocalDateTime.now());

            Double destLat = v.getRoute().getLatitudeDestino();
            Double destLon = v.getRoute().getLongitudeDestino();

            double distanceKm = calculateDistanceInKm(v.getLatitude(), v.getLongitude(), destLat, destLon);

            double averageSpeedKmH = 50.0;
            long remainingMinutes = (long) ((distanceKm / averageSpeedKmH) * 60);

            v.setEstimatedArrivalTime(LocalDateTime.now().plusMinutes(remainingMinutes));

            if (distanceKm < 0.1) {
                v.setState(VehicleState.CONCLUIDO);
            }

            repository.save(v);
        }
    }

    private double calculateDistanceInKm(double lat1, double lon1, double lat2, double lon2) {
        double p = 0.017453292519943295;
        double a = 0.5 - Math.cos((lat2 - lat1) * p) / 2 +
                Math.cos(lat1 * p) * Math.cos(lat2 * p) *
                        (1 - Math.cos((lon2 - lon1) * p)) / 2;

        return 12742 * Math.asin(Math.sqrt(a));
    }
}