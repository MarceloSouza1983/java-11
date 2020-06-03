package com.challenge.endpoints;

import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/submission")
public class SubmissionController {

    public SubmissionServiceInterface submissionService;
    public SubmissionMapper submissionMapper;

    @Autowired
    public SubmissionController(SubmissionServiceInterface submissionService, SubmissionMapper submissionMapper) {
        this.submissionService = submissionService;
        this.submissionMapper = submissionMapper;
    }

    @GetMapping
    public ResponseEntity<?> findByChallengeIdAndAccelerationId(@RequestParam(required = false) Long challengeId,
                                                                @RequestParam(required = false) Long accelerationId) {
        return new ResponseEntity<>(submissionMapper.map(this.submissionService
                .findByChallengeIdAndAccelerationId(challengeId, accelerationId)),
                HttpStatus.OK);
    }
}