package in.ashokit.service;

import in.ashokit.binding.EligResponse;

public interface EdService {

	public EligResponse determineEligibility(Long caseNum);
}
