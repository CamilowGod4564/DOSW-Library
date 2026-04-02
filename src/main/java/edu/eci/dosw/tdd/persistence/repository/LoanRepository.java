package edu.eci.dosw.tdd.persistence.repository;

import edu.eci.dosw.tdd.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<User, String> {
}
