package com.demo.postcodeservice.service;

import com.demo.postcodeservice.dto.PostcodeDto;
import com.demo.postcodeservice.exception.ResourceNotFoundException;
import com.demo.postcodeservice.model.DistanceResultResponse;
import com.demo.postcodeservice.model.Postcode;
import com.demo.postcodeservice.model.UpdateCoordinatesResponse;
import com.demo.postcodeservice.repo.PostcodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostcodeServiceTest {
@Mock
private PostcodeRepository postcodeRepository;
@Mock
private ModelMapper mockModelMapper;
private PostcodeService service;
private Postcode savedPostcode;
Optional<Postcode> postcode;

@BeforeEach
void setUp() {
	service = new PostcodeService(postcodeRepository);
	savedPostcode = Postcode.builder().latitude(0.0).longitude(0.0).build();
	// Configure PostcodeRepository.findByPostcode(...).
	postcode = Optional.of(Postcode.builder().latitude(0.0).longitude(0.0).build());
	ReflectionTestUtils.setField(service, "modelMapper", mockModelMapper);

}

@Test
void testUpdateCoordinates() {
	// Setup
	PostcodeDto postcodeDtoRes = PostcodeDto.builder().build();
	UpdateCoordinatesResponse expectedResult = new UpdateCoordinatesResponse();
	expectedResult.setMessage("coordinates are successfully updated");
	expectedResult.setPostcode(Postcode.builder().build());
	when(mockModelMapper.map(PostcodeDto.builder().build(), Postcode.class)).thenReturn(savedPostcode);
	when(postcodeRepository.findByPostcode("postcode")).thenReturn(postcode);
	when(postcodeRepository.save(Postcode.builder().build())).thenReturn(savedPostcode);
	// Run the test
	UpdateCoordinatesResponse result = service.updateCoordinates(postcodeDtoRes, "postcode");
	assertEquals(expectedResult, result);
}

@Test
void testUpdateCoordinates_ThrowsResourceNotFoundException() {
	when(postcodeRepository.findByPostcode("postcode")).thenThrow(ResourceNotFoundException.class);
	assertThrows(ResourceNotFoundException.class, () -> service.updateCoordinates(PostcodeDto.builder().build(), "postcode"));
}

@Test
void testCalculateDistance() {
	//set up
	DistanceResultResponse expectedResult = new DistanceResultResponse();
	expectedResult.setPostcodeFrom(Postcode.builder().build());
	expectedResult.setPostcodeTo(Postcode.builder().build());
	expectedResult.setDistance(0.0);
	expectedResult.setUnit("KM");
	when(postcodeRepository.findByPostcode("postcode")).thenReturn(postcode);
	//test run
	DistanceResultResponse result = service.calculateDistance("postcode", "postcode");
	assertEquals(expectedResult, result);
}

}