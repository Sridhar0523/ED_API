package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.DcEducationEntity;

public interface DcEducationRepository extends JpaRepository<DcEducationEntity, Serializable> {

	public DcEducationRepository findByCaseNum(Long caseNum);

	public static void findBycaseNum(Long caseNum) {
		// TODO Auto-generated method stub
		
	}
}
