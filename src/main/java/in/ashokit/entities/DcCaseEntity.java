package in.ashokit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DC_CASES")
@Data
public class DcCaseEntity {

	@Id
	@GeneratedValue
	private Integer caseNum;
	private Integer planId;
	private Integer appId;
}
