package com.demo.postcodeservice.model;

import lombok.Data;

@Data
public class DistanceResultResponse {
Postcode postcodeFrom;
Postcode postcodeTo;
double distance;
String unit;
}
