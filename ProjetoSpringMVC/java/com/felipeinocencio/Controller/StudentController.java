package com.felipeinocencio.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.felipeinocencio.Dao.StudentDao;
import com.felipeinocencio.Entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@RestController
public class StudentController {

    private Map<Integer, Student> students;
    private StudentDao studentDao = new StudentDao();

    public StudentController() throws SQLException {
        students = new HashMap<Integer, Student>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/student", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listar() throws SQLException {
        int index=0;

        List<Student> studentsGetted = new ArrayList<Student>();
        students = new HashMap<Integer, Student>();

        studentsGetted = studentDao.getLista();

        for (Student a : studentsGetted) {
            students.put(index, a);
            index++;
        }

        return new ResponseEntity<List<Student>>(new ArrayList<Student>(students.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/student/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> buscar(@PathVariable("id") int id) throws SQLException {

        Student student = studentDao.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) {
        studentDao.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/student", method = RequestMethod.POST)
    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws JsonParseException, JsonMappingException, IOException, SQLException {

        studentDao.adiciona(student);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {

        studentDao.altera(student, id);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }
}
