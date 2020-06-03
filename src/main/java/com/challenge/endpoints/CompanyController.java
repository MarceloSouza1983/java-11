package com.challenge.endpoints;

import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.interfaces.CompanyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    public CompanyServiceInterface companyService;

    @Autowired
    public CompanyController(CompanyServiceInterface companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findByAccelerationIdOrUserId(@RequestParam(required = false) Long accelerationId,
                                                          @RequestParam(required = false) Long userId) {
        if(accelerationId != null) {
            return new ResponseEntity<>(this.companyService.findByAccelerationId(accelerationId), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.companyService.findByUserId(userId), HttpStatus.OK);
    }

}