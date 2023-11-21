package com.demo.postcodeservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostcodeDto {

private double latitude;
private double longitude;

}

