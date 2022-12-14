package io.github.nataelienai.dronefeeder.exception;

/**
 * Response pattern for any exception that occurred during the API runtime.
 */
public class ApiError {

  private final String message;

  public ApiError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
