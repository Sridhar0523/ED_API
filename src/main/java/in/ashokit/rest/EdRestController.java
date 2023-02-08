package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.EligResponse;
import in.ashokit.service.EdService;

@RestController
public class EdRestController {

	@Autowired
	private EdService edService;
	
	@GetMapping("/eligibility/{caseNum}")
	public ResponseEntity<EligResponse> determine(@PathVariable Long caseNum) {
		EligResponse response = edService.determineEligibility(caseNum);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
