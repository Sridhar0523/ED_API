package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.EdEligDtlsEntity;

public interface EdEligRepository extends JpaRepository<EdEligDtlsEntity, Serializable> {

	
}
