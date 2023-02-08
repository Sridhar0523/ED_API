package in.ashokit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CO_TRIGGERS")
@Data
public class CoTrgEntity {

	@Id
	@GeneratedValue
	private Integer coTrgId;

	private Long caseNum;

	private byte[] pdf;

	private String trgStatus;
}
