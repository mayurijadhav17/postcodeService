package com.demo.postcodeservice.repo;


import com.demo.postcodeservice.exception.ResourceNotFoundException;
import com.demo.postcodeservice.model.Postcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostcodeRepository extends JpaRepository<Postcode, Long> {
  Optional<Postcode> findByPostcode(String postcode) throws ResourceNotFoundException;
 
}