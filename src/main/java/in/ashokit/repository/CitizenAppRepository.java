package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.CitizenAppEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable> {

}
