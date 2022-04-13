package universty.assignment.management.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import universty.assignment.management.student.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findAllByStudentId(String id);
}
