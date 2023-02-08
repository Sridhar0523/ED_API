package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.PlanEntity;

public interface PlanRepository extends JpaRepository<PlanEntity, Serializable> {

}
