package com.ravi.cruddemo;

import com.ravi.cruddemo.dao.StudentDAO;
import com.ravi.cruddemo.dao.StudentDAOImpl;
import com.ravi.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);

		System.out.println("********************************");
		System.out.println("Started CrudDemo Successfully!!!");
		System.out.println("********************************");
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAOImpl studentDAOImpl){
		return runner -> {
			System.out.println("Started CommandLineRunner!!!");

//			saveStudent(studentDAOImpl);
//			saveMultipleStudent(studentDAOImpl);
//			readStudent(studentDAOImpl);
//			findAllStudents(studentDAOImpl);
//			findByFirstName(studentDAOImpl);
//			update(studentDAOImpl);
//			delete(studentDAOImpl);
			deleteAll(studentDAOImpl);
		};
	}

	private void saveStudent(StudentDAO studentDAO) {
		Student student = new Student();

		student.setFirstName("Krishna");
		student.setLastName("Yadav");
		student.setEmail("krishna.yadav@godly.com");

		System.out.println("Going to save student: " + student);
		studentDAO.save(student);
		System.out.println("Saved student with id: " + student.getId());
	}

	private void saveMultipleStudent(StudentDAO studentDAO) {

		Student student1 = new Student();
		Student student2 = new Student();
		Student student3 = new Student();

		student1.setFirstName("Krishna");
		student1.setLastName("Yadav");
		student1.setEmail("krishna.yadav@godly.com");
		student2.setFirstName("Ram");
		student2.setLastName("Raghuvanshi");
		student2.setEmail("ram.raghuvanshi@godly.com");
		student3.setFirstName("Balram");
		student3.setLastName("Yadav");
		student3.setEmail("balram.yadav@godly.com");

		System.out.println("Going to save student1: " + student1);
		System.out.println("Going to save student2: " + student2);
		System.out.println("Going to save student3: " + student3);

		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

		System.out.println("Student saved successfully!!!");
		System.out.println("Saved student1 with id: " + student1.getId());
		System.out.println("Saved student2 with id: " + student2.getId());
		System.out.println("Saved student3 with id: " + student3.getId());
	}

	private void readStudent(StudentDAO studentDAO) {
		long id = 5;

		System.out.println("Going to fetch student with id: " + id);
		Student student = studentDAO.findById(id);
		System.out.println("Fetched student with id: " + id + ", details: " + student);

	}

	private void findAllStudents(StudentDAO studentDAO){
		System.out.println("Goind to fetch all the students.");
		List<Student> students = studentDAO.findAll();

		for (Student student: students){
			System.out.println("student: " + student);
		}
	}

	private void findByFirstName(StudentDAO studentDAO){
		String firstName = "Krishna";

		System.out.println("Goind to fetch all the students with firstName: " + firstName);
		List<Student> students = studentDAO.findByFirstName(firstName);

		for (Student student: students){
			System.out.println("student: " + student);
		}
	}

	private void update(StudentDAO studentDAO){
		long id = 5;

		System.out.println("Going to update data for student_id: " + id);
		Student student = studentDAO.findById(id);

		student.setFirstName("Shri Krishna");
		System.out.println("Updated student name: " + student.getFirstName());
		System.out.println("Going to save student");

		studentDAO.update(student);
		System.out.println("Updated student: " + student);
	}

	private void delete(StudentDAO studentDAO){
		long id = 5;

		System.out.println("Going to delete data for student_id: " + id);
		studentDAO.delete(id);
	}

	private void deleteAll(StudentDAO studentDAO){
		System.out.println("Going to delete all students!!!");
		studentDAO.deleteAll();
	}
}
