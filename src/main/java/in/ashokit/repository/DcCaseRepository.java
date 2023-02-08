package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.DcCaseEntity;

public interface DcCaseRepository extends JpaRepository<DcCaseEntity, Serializable> {

	public DcCaseEntity findByAppId(Integer appId);
}
