package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import commons.entities.UserMaster;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, String>{}
