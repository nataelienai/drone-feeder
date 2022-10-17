package io.github.nataelienai.dronefeeder.delivery;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import io.github.nataelienai.dronefeeder.drone.Drone;

@Entity
@Table(name = "delivery")
public class Delivery {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "status", nullable = false)
  private Integer status;

  @Column(name = "status_last_modified", nullable = false)
  private Instant statusLastModified;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "drone_id", nullable = true)
  private Drone drone;

  public Long getId() {
    return id;
  }

  public Status getStatus() {
    return Status.valueOf(status);
  }

  public void setStatus(Status status) {
    this.status = status.getCode();
  }

  public Instant getStatusLastModified() {
    return statusLastModified;
  }

  public void setStatusLastModified(Instant statusLastModified) {
    this.statusLastModified = statusLastModified;
  }

  public Drone getDrone() {
    return drone;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

}