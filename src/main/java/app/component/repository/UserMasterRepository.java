package app.component.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.commons.entities.UserMaster;

/**
 * ユーザ情報の検索、永続化を担うJPA
 */
@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Long>{}
