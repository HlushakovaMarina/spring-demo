package hlushakovaM.spring_demo.repository;

import hlushakovaM.spring_demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
