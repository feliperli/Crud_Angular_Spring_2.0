package com.felipeinocencio.Dao;

import com.felipeinocencio.Entity.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class StudentDao{

    private static Map<Integer, Student> students;
    private Connection connection;

    public StudentDao() throws SQLException{
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Student student) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO student (id, name, course, email) VALUES (?, ?, ?, ?)");
        // seta os valores
        stmt.setInt(1,student.getId());
        stmt.setString(2,student.getName());
        stmt.setString(3,student.getCourse());
        stmt.setString(4,student.getEmail());

        // executa
        stmt.execute();
        stmt.close();
    }

    public List<Student> getLista() throws SQLException {

        List<Student> students = new ArrayList<Student>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM student");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // criando o objeto Aluno
            Student student = new Student();

            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setCourse(rs.getString("course"));
            student.setEmail(rs.getString("email"));

            // adicionando o objeto à lista
            students.add(student);

        }

        rs.close();
        stmt.close();

        return students;

    }

    public Student getStudent(int id) throws SQLException {

        Student student = new Student();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM student WHERE " + "id = ?");

            stmt.setInt(1, id); //Note que essa variavel é passada da função principal
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setCourse(rs.getString("course"));
                student.setEmail(rs.getString("email"));
            }
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (student);

    }


    public void excluir(int id) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM student WHERE id = ?");
            stmt.setInt(1, id);
            stmt.execute();

        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }


    public void altera(Student student, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE student SET id=?, name=?, course=?, email=? WHERE id=?");

        stmt.setInt(1, student.getId());
        stmt.setString(2, student.getName());
        stmt.setString(3, student.getCourse());
        stmt.setString(4, student.getEmail());
        stmt.setInt(6, id);

        stmt.execute();
        stmt.close();
    }
}
