package com.demo.postcodeservice.model;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateCoordinatesResponse {
String message;
Postcode postcode;
}
