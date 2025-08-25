package hlushakovaM.spring_demo.controller;

import hlushakovaM.spring_demo.model.Student;
import hlushakovaM.spring_demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        int age = student.getAge();
        student.setAge(age + 10);
        return repository.save(student);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Optional<Student> student = repository.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent){
        Optional<Student> student = repository.findById(id);
        if(student.isPresent()){
            Student student1 = student.get();
            student1.setFistName(updatedStudent.getFistName());
            student1.setLastName(updatedStudent.getLastName());
            student1.setAge(updatedStudent.getAge());
            return ResponseEntity.ok(repository.save(student1));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/name/{name}")
    public List<Student> getStudentsByName(@PathVariable String name){
       return repository.findByFirstName(name);
    }
}
