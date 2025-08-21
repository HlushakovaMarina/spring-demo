package hlushakovaM.spring_demo.controller;

import hlushakovaM.spring_demo.model.Student;
import hlushakovaM.spring_demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:63342")
public class StudentController {
    private final StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return repository.findAll();
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student){
        int age = student.getAge();
        student.setAge(age + 10);
        return repository.save(student);
    }
}
