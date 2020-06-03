package com.challenge.endpoints;

import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findByCompanyIdOrAccelerationId(@RequestParam(required = false) Long companyId,
                                                             @RequestParam(required = false) String accelerationName) {
        if (companyId != null) {
            return new ResponseEntity<>(this.userService.findByCompanyId(companyId), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.userService.findByAccelerationName(accelerationName), HttpStatus.OK);
    }

}