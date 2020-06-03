package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeControler {

    @Autowired
    private ChallengeServiceInterface challengeService;

    @GetMapping
    public ResponseEntity<List<Challenge>> all(@RequestParam(name = "accelerationId", required = false) String accId,
                                               @RequestParam(name = "userId", required = false) String userId) {
        if(accId != null && userId != null) {
            return new ResponseEntity<>(challengeService.findByAccelerationIdAndUserId(Long.valueOf(accId),
                Long.valueOf(userId)), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
    }

}