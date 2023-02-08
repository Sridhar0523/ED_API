package in.ashokit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DC_INCOME")
@Data
public class DcIncomeEntity {

	@Id
	@GeneratedValue
	private Integer incomeId;
	private Double salaryIncome;
	private Double rentIncome;
	private Double propertyIncome;
	
	private Long caseNum;
}
