package hlushakovaM.spring_demo.repository;

import hlushakovaM.spring_demo.model.Group;
import hlushakovaM.spring_demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long > {
    List<Group> findByName(String name);

}
