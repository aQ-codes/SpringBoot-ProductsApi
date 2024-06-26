package rest_project.apiproduct.repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import rest_project.apiproduct.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
   
    Optional<Role> findByAuthority(String authority);
   
}
