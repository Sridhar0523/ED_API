package in.ashokit.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DC_KIDS")
@Data
public class DcKidEntity {

	@Id
	@GeneratedValue
	private Integer kidId;
	private String kidName;
	private LocalDate kidDob;
	private Long kidSsn;
	private String kidGender;
	private Long caseNum;
}
