package in.ashokit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PLAN_MASTER")
@Data
public class PlanEntity {

	@Id
	@GeneratedValue
	private Integer planId;
	private String planName;
}
