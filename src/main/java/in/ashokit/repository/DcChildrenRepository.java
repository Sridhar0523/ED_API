package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.DcKidEntity;

public interface DcChildrenRepository extends JpaRepository<DcKidEntity, Serializable> {

}
