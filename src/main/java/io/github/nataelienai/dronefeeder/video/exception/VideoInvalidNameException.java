package io.github.nataelienai.dronefeeder.video.exception;

/**
 * Thrown when a video file name is invalid.
 */
public class VideoInvalidNameException extends RuntimeException {

  public VideoInvalidNameException(String message) {
    super(message);
  }

}
