package io.github.nataelienai.dronefeeder.video;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.nataelienai.dronefeeder.delivery.Delivery;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Video entity.
 */
@Entity
@Table(name = "video")
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String fileName;

  private Long size;

  @Lob
  private String base64;

  @OneToOne(mappedBy = "video", fetch = FetchType.LAZY,
          cascade = CascadeType.ALL, optional = true)

  private Delivery delivery;

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
  }

  public Long getId() {
    return id;
  }

  @JsonIgnore
  public String getBase64() {
    return base64;
  }

  public void setBase64(String base64) {
    this.base64 = base64;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }
}
