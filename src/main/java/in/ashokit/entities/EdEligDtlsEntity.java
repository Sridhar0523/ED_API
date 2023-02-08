package in.ashokit.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ELIG_DTLS")
@Data
public class EdEligDtlsEntity {
	
	@Id
	@GeneratedValue
	private Integer edTraceId;
	private Long caseNum;
	private String holderName;
	private Long holderSsn;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitAmt;
	private String denialReason;
	
	
	

}
