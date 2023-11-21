package com.demo.postcodeservice.controller;

import com.demo.postcodeservice.dto.PostcodeDto;
import com.demo.postcodeservice.model.DistanceResultResponse;
import com.demo.postcodeservice.model.UpdateCoordinatesResponse;
import com.demo.postcodeservice.service.PostcodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostcodeController {
private final PostcodeService postcodeService;

@GetMapping("/distance/{postcode1}/to/{postcode2}")
public DistanceResultResponse calculateDistance(@PathVariable String postcode1, @PathVariable String postcode2) {
	return postcodeService.calculateDistance(postcode1, postcode2);
}

@PostMapping("/update/{postcode}")
public UpdateCoordinatesResponse updatePostcodeAttributes(@RequestBody PostcodeDto postcodeObj, @PathVariable String postcode) {
	return postcodeService.updateCoordinates(postcodeObj, postcode);
}

}