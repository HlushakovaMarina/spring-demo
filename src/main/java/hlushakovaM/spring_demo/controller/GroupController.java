package hlushakovaM.spring_demo.controller;

import hlushakovaM.spring_demo.model.Group;
import hlushakovaM.spring_demo.model.Student;
import hlushakovaM.spring_demo.repository.GroupRepository;
import hlushakovaM.spring_demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/groups")
public class GroupController {
    private final GroupRepository repository;

    @Autowired
    public GroupController(GroupRepository repository) {
        this.repository = repository;
    }
    @PostMapping
    public Group addGroup (@RequestBody Group group){
        return repository.save(group);
    }
    @GetMapping
    public List<Group> getAllCourses(){
        return repository.findAll();
    }
    @GetMapping("/name/{name}")
    public List<Group> getByName(@PathVariable String name){
        return repository.findByName(name);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Group> getById(@PathVariable Long id){
        Optional<Group> group = repository.findById(id);
        return group.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group updatedGroup){
        Optional<Group> group = repository.findById(id);
        if(group.isPresent()){
            Group group1 = group.get();
            group1.setName(updatedGroup.getName());
            group1.setStudents(updatedGroup.getStudents());
            return ResponseEntity.ok(repository.save(group1));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
