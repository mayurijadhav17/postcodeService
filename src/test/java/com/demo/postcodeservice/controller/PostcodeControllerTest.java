package com.demo.postcodeservice.controller;

import com.demo.postcodeservice.dto.PostcodeDto;
import com.demo.postcodeservice.model.DistanceResultResponse;
import com.demo.postcodeservice.model.Postcode;
import com.demo.postcodeservice.model.UpdateCoordinatesResponse;
import com.demo.postcodeservice.service.PostcodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostcodeControllerTest {
@Mock
private PostcodeService service;
private PostcodeController postcodeController;

@BeforeEach
void setUp() {
	postcodeController = new PostcodeController(service);
}

@Test
void testCalculateDistance() {
	// Setup
	Postcode postcodeFrom = Postcode.builder().postcode("postcodeFrom").latitude(0.0).longitude(0.0).build();
	Postcode postcodeTo = Postcode.builder().postcode("postcodeTo").latitude(0.0).longitude(0.0).build();
	DistanceResultResponse expectedResult = new DistanceResultResponse();
	expectedResult.setPostcodeFrom(postcodeFrom);
	expectedResult.setPostcodeTo(postcodeTo);
	//tests
	when(service.calculateDistance("postcode1", "postcode2")).thenReturn(expectedResult);
	DistanceResultResponse result = postcodeController.calculateDistance("postcode1", "postcode2");
	assertEquals(expectedResult, result);
}

@Test
void testUpdatePostcodeAttributes() {
	// Setup
	UpdateCoordinatesResponse expectedResult = new UpdateCoordinatesResponse();
	expectedResult.setMessage("message");
	expectedResult.setPostcode(Postcode.builder().build());
	// Configure PostcodeService.updateCoordinates(...).
	UpdateCoordinatesResponse updateCoordinatesResponse = new UpdateCoordinatesResponse();
	updateCoordinatesResponse.setMessage("message");
	updateCoordinatesResponse.setPostcode(Postcode.builder().build());
	PostcodeDto postcodeDto = PostcodeDto.builder().build();
//test
	when(service.updateCoordinates(postcodeDto, "postcode")).thenReturn(updateCoordinatesResponse);
	UpdateCoordinatesResponse result = postcodeController.updatePostcodeAttributes(postcodeDto, "postcode");
	assertEquals(expectedResult, result);
}
}
