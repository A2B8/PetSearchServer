package com.project.SFP.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository
        extends JpaRepository<PUser, Long> {
    Optional<PUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE PUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}
