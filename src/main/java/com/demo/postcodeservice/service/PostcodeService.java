package com.demo.postcodeservice.service;

import com.demo.postcodeservice.dto.PostcodeDto;
import com.demo.postcodeservice.exception.ResourceNotFoundException;
import com.demo.postcodeservice.model.DistanceResultResponse;
import com.demo.postcodeservice.model.Postcode;
import com.demo.postcodeservice.model.UpdateCoordinatesResponse;
import com.demo.postcodeservice.repo.PostcodeRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class PostcodeService {
private final PostcodeRepository postcodeRepository;

@Autowired
private ModelMapper modelMapper;
private final static long EARTH_RADIUS = 6371;
private final static String UNIT = "KM";

@Autowired
public PostcodeService(PostcodeRepository postcodeRepository) {
	this.postcodeRepository = postcodeRepository;
}

public UpdateCoordinatesResponse updateCoordinates(PostcodeDto postcodeDtoRes, String postcode) {
	log.info("Inside method updateCoordinates()");
	log.debug("postcode requested to updated coordinates",postcode);
	Postcode postcodeEntityObj = modelMapper.map(postcodeDtoRes, Postcode.class);
	Postcode postcodeObj = postcodeRepository.findByPostcode(postcode).map(postcodeUpdatedObj->{ postcodeUpdatedObj.setLongitude(postcodeEntityObj.getLongitude());postcodeUpdatedObj.setLatitude(postcodeEntityObj.getLatitude());	return postcodeRepository.save(postcodeUpdatedObj);}).orElseThrow(() -> new ResourceNotFoundException("Postcode does not exist!!" + postcode));
	UpdateCoordinatesResponse updateCoordinatesResponse = new UpdateCoordinatesResponse();
	updateCoordinatesResponse.setPostcode(postcodeObj);
	updateCoordinatesResponse.setMessage("coordinates are successfully updated");
	return updateCoordinatesResponse;
}

public DistanceResultResponse calculateDistance(String postcodeFrom, String postcodeTo) {
	log.info("Inside method calculateDistance()");
	log.debug("Calculating distance between",postcodeFrom ,"to", postcodeTo);
	Postcode postcodeFromObj = postcodeRepository.findByPostcode(postcodeFrom).orElseThrow(() -> new ResourceNotFoundException("Postcode does not exist!! " + postcodeFrom));
	Postcode postcodeToObj = postcodeRepository.findByPostcode(postcodeTo).orElseThrow(() -> new ResourceNotFoundException("Postcode does not exist!! " + postcodeTo));
	DistanceResultResponse resultObject = new DistanceResultResponse();
	resultObject.setPostcodeFrom(postcodeFromObj);
	resultObject.setPostcodeTo(postcodeToObj);
	resultObject.setDistance(totalDistance(postcodeFromObj,postcodeToObj));
	resultObject.setUnit(UNIT);
	return resultObject;
}

private double totalDistance(Postcode postcodeFromObj, Postcode postcodeToObj) {
	double lon1Radians = Math.toRadians(postcodeFromObj.getLongitude());
	double lon2Radians = Math.toRadians(postcodeToObj.getLongitude());
	double lat1Radians = Math.toRadians(postcodeFromObj.getLatitude());
	double lat2Radians = Math.toRadians(postcodeToObj.getLatitude());
	double a = haversine(lat1Radians, lat2Radians) + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	return EARTH_RADIUS * c;
}
private double haversine(double degree1, double degree2) {
	return square(Math.sin((degree1 - degree2) / 2.0));
}

private double square(double x) {
	return x * x;
}

}



