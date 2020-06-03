package com.challenge.endpoints;

import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    private AccelerationServiceInterface accelerationService;

    @Autowired
    public AccelerationController(AccelerationServiceInterface accelerationService) {
        this.accelerationService = accelerationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.accelerationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acceleration")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findByCompanyId(@RequestParam Long companyId) {
        return new ResponseEntity<>(this.accelerationService.findByCompanyId(companyId), HttpStatus.OK);
    }

}
