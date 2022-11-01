package redisspringexample.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentService {

    private final RedisTemplate template;
    public static final String HASH_KEY = "student";

    public Student save(Student student) {
        template.opsForHash().put(HASH_KEY, student.getId(), student);
        return student;
    }

    public List<Student> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public String delete(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "Operation completed successfully!";
    }

    public Student findById(int id) {
        return (Student) template.opsForHash().get(HASH_KEY, id);
    }

}
