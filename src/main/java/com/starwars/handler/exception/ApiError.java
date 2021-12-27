package com.starwars.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ApiError implements Serializable {

  private static final long serialVersionUID = 1L;

  private String message;
  private int status;
  private LocalDate date;
}
