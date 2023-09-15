package com.inventory.Repository;

import com.inventory.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {
    Optional<LocalUser> findByEmailIgnoreCase(String email); // Updated method name
    Optional<LocalUser> findByUsernameIgnoreCase(String username); // Keep this method as well
}

