package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import com.challenge.service.interfaces.CandidateServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    CandidateMapper mapper;

        @GetMapping
        ResponseEntity<List<CandidateDTO>> getCandidateByCompanyId(@RequestParam(name = "companyId") Optional<Long> companyId) {
            return ResponseEntity.ok(mapper.map(this.candidateService.findByCompanyId(companyId.get())));
        }

    @GetMapping("/{user}/{acceleration}/{company}")
    ResponseEntity<CandidateDTO> getCandidateByUserAccelerationCompanyId(
            @PathVariable("user") Long userID,
            @PathVariable("acceleration") Long accelerationID,
            @PathVariable("company") Long companyID){
        Optional<Candidate> optionalCandidate = this.candidateService.findById(userID,companyID,accelerationID);
        if(optionalCandidate.isPresent()) return ResponseEntity.ok(mapper.map(optionalCandidate.get()));
        return ResponseEntity.notFound().build();
    }

}