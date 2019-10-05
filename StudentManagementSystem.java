import java.util.*;
public interface StudentManagementSystem {
boolean enrollStudent(StudentDetails student,String deptName);
ArrayList<StudentDetails> getStudentsByDepartment(String deptName);
void displayStudents(String deptName);
}
