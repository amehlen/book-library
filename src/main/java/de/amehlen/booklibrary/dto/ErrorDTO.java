package de.amehlen.booklibrary.dto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(setterPrefix = "with")
public class ErrorDTO {

  private String title;
  private String message;
  private int status;
  private String errorType;
  private final String timestamp = ZonedDateTime
      .now()
      .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z Z"));

}
