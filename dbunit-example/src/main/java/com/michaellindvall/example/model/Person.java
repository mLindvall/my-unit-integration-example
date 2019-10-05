package com.michaellindvall.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
  private String firstName;
  private String lastName;
  private Integer age;
}
