package io.github.nataelienai.dronefeeder.drone;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.nataelienai.dronefeeder.drone.exception.DroneNotFoundException;

@Service
public class DroneService {

  @Autowired
  private DroneRepository droneRepository;

  @Transactional
  public Drone create(Drone drone) {
    return droneRepository.save(drone);
  }

  public List<Drone> findAll() {
    return droneRepository.findAll();
  }

  public Drone findById(Long id) {
    Optional<Drone> optionalDrone = droneRepository.findById(id);
    if (optionalDrone.isEmpty()) {
      throw new DroneNotFoundException("Drone not found.");
    }
    return optionalDrone.get();
  }

  @Transactional
  public Drone update(Long id, Drone updatedDrone) {
    Optional<Drone> optionalDrone = droneRepository.findById(id);
    if (optionalDrone.isEmpty()) {
      throw new DroneNotFoundException("Drone not found.");
    }
    Drone drone = optionalDrone.get();
    drone.setLatitude(updatedDrone.getLatitude());
    drone.setLongitude(updatedDrone.getLongitude());
    return drone;
  }

  @Transactional
  public void delete(Long id) {
    boolean droneExists = droneRepository.existsById(id);
    if (!droneExists) {
      throw new DroneNotFoundException("Drone not found.");
    }
    droneRepository.deleteById(id);
  }
}