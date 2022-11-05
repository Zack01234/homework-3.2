package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private long idGenerator = 1;

    public Student create(Student student) {
        student.setId(idGenerator++);
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student read(long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return studentMap.get(id);
    }

    public Student update(long id, Student student) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        Student oldStudent = studentMap.get(id);
        oldStudent.setAge(student.getAge());
        oldStudent.setName(student.getName());
        studentMap.replace(id, oldStudent);
        return oldStudent;
    }

    public Student delete(long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return studentMap.remove(id);
    }

    public Collection<Student> findByAge(int age) {
        return studentMap.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
