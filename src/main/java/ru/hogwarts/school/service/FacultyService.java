package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private long idGenerator = 1;

    public Faculty create(Faculty faculty) {
        faculty.setId(idGenerator++);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty read(long id) {
        if (!facultyMap.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        return facultyMap.get(id);
    }

    public Faculty update(long id, Faculty faculty) {
        if (!facultyMap.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        Faculty oldFaculty = facultyMap.get(id);
        oldFaculty.setName(faculty.getName());
        oldFaculty.setColor(faculty.getColor());
        facultyMap.replace(id, oldFaculty);
        return oldFaculty;
    }

    public Faculty delete(long id) {
        if (!facultyMap.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        return facultyMap.remove(id);
    }

    public List<Faculty> findByColor(String color) {
        return facultyMap.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
