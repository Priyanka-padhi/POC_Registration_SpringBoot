package io.tntra.e_site.repository;

import io.tntra.e_site.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}

