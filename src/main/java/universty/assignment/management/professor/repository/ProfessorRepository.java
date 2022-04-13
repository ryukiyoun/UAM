package universty.assignment.management.professor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import universty.assignment.management.professor.entity.Professor;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findAllByProfessorId(String name);
}
