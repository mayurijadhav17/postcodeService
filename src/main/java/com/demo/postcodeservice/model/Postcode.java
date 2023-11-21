package com.demo.postcodeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "postcode")
@Data
public class Postcode {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @JsonIgnore
 private Long id;
 private String postcode;
 private double latitude;
 private double longitude;
 
}

