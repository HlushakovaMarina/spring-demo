package hlushakovaM.spring_demo.repository;

import hlushakovaM.spring_demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String firstName);// именованный запрос в стринге
}
