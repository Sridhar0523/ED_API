package in.ashokit.service;

import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.EligResponse;
import in.ashokit.entities.CitizenAppEntity;
import in.ashokit.entities.CoTrgEntity;
import in.ashokit.entities.DcCaseEntity;
import in.ashokit.entities.DcEducationEntity;
import in.ashokit.entities.DcIncomeEntity;
import in.ashokit.entities.DcKidEntity;
import in.ashokit.entities.EdEligDtlsEntity;
import in.ashokit.entities.PlanEntity;
import in.ashokit.repository.CitizenAppRepository;
import in.ashokit.repository.CoTriggerRepository;
import in.ashokit.repository.DcCaseRepository;
import in.ashokit.repository.DcEducationRepository;
import in.ashokit.repository.DcIncomeRepository;
import in.ashokit.repository.DcKidRepository;
import in.ashokit.repository.EdEligRepository;
import in.ashokit.repository.PlanRepository;

@Service
public class EdServiceImpl implements EdService {

	@Autowired
	private DcCaseRepository dcCaseRepository;

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private DcIncomeRepository dcIncomeRepository;

	@Autowired
	private DcKidRepository dcKidRepository;

	@Autowired
	private CitizenAppRepository citizenAppRepository;

	@Autowired
	private DcEducationRepository dcEducationRepository;

	@Autowired
	private CoTriggerRepository coTriggerRepository;
	
	@Autowired
	private EdEligRepository edEligRepository;
	

	boolean ageStatus = true;
	boolean noKids = false;
	boolean ageflag = true;

	@Override
	public EligResponse determineEligibility(Long caseNum) {

		EligResponse response = new EligResponse();

		Integer planId = null;
		String planName = null;
		Integer appId = null;

		Optional<DcCaseEntity> dcCaseEntity = dcCaseRepository.findById(caseNum);

		if (dcCaseEntity.isPresent()) {
			planId = dcCaseEntity.get().getPlanId();
			appId = dcCaseEntity.get().getAppId();
		}
//		else {
//			return null;
//		}

		Optional<PlanEntity> planEntity = planRepository.findById(planId);
		if (planEntity.isPresent()) {
			planName = planEntity.get().getPlanName();
		}

		Optional<CitizenAppEntity> citizenAppEntity = citizenAppRepository.findById(appId);
		CitizenAppEntity citizenApp = citizenAppEntity.get();

		DcIncomeEntity income = dcIncomeRepository.findByCaseNum(caseNum);

		List<DcKidEntity> kids = dcKidRepository.findByCaseNum(caseNum);

		if ("SNAP".equals(planName)) {

			if (income.getSalaryIncome() > 300) {
				// response.setPlanStatus("Denied");
				response.setDenialReason("High Income");
			}

		} else if ("CCAP".equals(planName)) {

			// boolean eligible = true;
			// boolean noKidsFlag = true;
			// boolean ageFlag = true;

			if (!kids.isEmpty()) {

				kids.forEach(kid -> {
					LocalDate dob = kid.getKidDob();
					LocalDate today = LocalDate.now();

					Period period = Period.between(dob, today);
					int year = period.getYears();

					if (year > 16) {
						ageflag = false;

					}
				});
			} else {
				response.setDenialReason("No Kids Available");
				noKids = true;
			}

			if (income.getSalaryIncome() > 300) {
				response.setDenialReason("High Income");
			}

			if (noKids && income.getSalaryIncome() > 300) {
				response.setDenialReason("High Income + No Kids");
			}

			if (!ageflag) {
				response.setDenialReason("Kids Age > 16");
			}

			if (income.getSalaryIncome() > 300 && !ageflag) {
				response.setDenialReason("High Income + Kids Age > 16");
			}

		} else if ("Medicaid".equals(planName)) {

			Double salaryIncome = income.getSalaryIncome();
			Double rentIncome = income.getRentIncome();
			Double propertyIncome = income.getPropertyIncome();

			if (salaryIncome > 300) {
				response.setDenialReason("High Salary Income");
			}

			if (rentIncome > 0) {
				response.setDenialReason("Rental Income Available");
			}

			if (propertyIncome > 0) {
				response.setDenialReason("Property Income Available");
			}

			if (rentIncome > 0 && propertyIncome > 0) {
				response.setDenialReason("Rental + Property Income Available");
			}

			if (salaryIncome > 300 && rentIncome > 0 && propertyIncome > 0) {
				response.setDenialReason("High Income + Rental + Property Income Available");
			}

		} else if ("Medicare".equals(planName)) {

			LocalDate dob = citizenApp.getDob();
			LocalDate now = LocalDate.now();

			Period between = Period.between(dob, now);
			int years = between.getYears();

			if (years < 65) {
				response.setDenialReason("Age < 65 years");
			}

		} else if ("RIW".equals(planName)) {

			DcEducationEntity educationEntity = dcEducationRepository.findByCaseNum(caseNum);
			Integer graduationYear = educationEntity.getGraduationYear();

			if (graduationYear <= 0) {
				response.setDenialReason("Not Graduated");
			}

			if (income.getSalaryIncome() > 0) {
				response.setDenialReason("Already an Employee");
			}
		}

		response.setPlanName(planName);
		if (response.getDenialReason() != null) {
			response.setPlanStatus("Denied");
		} else {
			response.setPlanStatus("Approved");
			response.setPlanStartDate(LocalDate.now().plusDays(1));
			response.setPlanEndDate(LocalDate.now().plusMonths(3));
			response.setBenefitAmt(350.00);
		}

		EdEligDtlsEntity edEligDtlsEntity = new EdEligDtlsEntity();
		BeanUtils.copyProperties(response, edEligDtlsEntity);
		edEligRepository.save(edEligDtlsEntity);
		
		CoTrgEntity coTrgEntity = new CoTrgEntity();
		coTrgEntity.setCaseNum(caseNum);
		coTrgEntity.setTrgStatus("Pending");
		coTriggerRepository.save(coTrgEntity);

		return response;
	}

}
