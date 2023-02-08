package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.DcKidEntity;

public interface DcKidRepository extends JpaRepository<DcKidEntity, Serializable> {

	public List<DcKidEntity> findByCaseNum(Long caseNum);
}
