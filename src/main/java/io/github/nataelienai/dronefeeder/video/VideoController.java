package io.github.nataelienai.dronefeeder.video;

import java.io.IOException;

import io.github.nataelienai.dronefeeder.delivery.exception.DeliveryNotFoundException;
import io.github.nataelienai.dronefeeder.video.exception.VideoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for handling video resource requests.
 */
@RestController
@RequestMapping("/video")
public class VideoController {

  private final VideoService videoService;

  @Autowired
  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  /**
   * Upload the file.
   *
   * @param file the file of the video to upload.
   * @return the uploaded file.
   * @throws IOException in case of an access error.
   */
  @PostMapping("/upload")
  @ResponseStatus(HttpStatus.OK)
  public Video upload(@RequestBody MultipartFile file) throws IOException {
    return videoService.upload(file);
  }

  /**
   * Download the file.
   *
   * @param id the id of the file to download.
   * @return the file for download.
   * @throws VideoNotFoundException if a video with {@literal id} does not exist.
   */
  @PostMapping("/download/{id}")
  public ResponseEntity<byte[]> download(@PathVariable Long id) {
    byte[] decodedVideo = videoService.download(id);
    return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.parseMediaType("video/mp4"))
            .header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    String.format("attachment; filename=delivery_video_%s.%s", id, "mp4")
            )
            .body(decodedVideo);
  }

}