package com.heesuk.spring.webflux.jwt.example.dto;

import lombok.Getter;

@Getter
public class HelloUser {

  private final Object userId;
  private final String message;

  public HelloUser(Object userId) {
    this.userId = userId;
    this.message = "HELLO WORLD! " + userId;
  }

}
