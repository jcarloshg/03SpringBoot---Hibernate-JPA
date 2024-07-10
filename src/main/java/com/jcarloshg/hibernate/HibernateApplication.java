package com.jcarloshg.hibernate;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jcarloshg.hibernate.dao.StudentDAO;
import com.jcarloshg.hibernate.entities.Student;

@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);
			// readStudent(studentDAO);
			// getAllByQuery(studentDAO);
			// getStudentByLastName(studentDAO);
			updateStudent(studentDAO);
		};
	}

	private void updateStudent(StudentDAO studentDAO) {
		int id = 3;
		Student student = studentDAO.findByID(id);
		student.setFirst_name("Pedro Pedro");
		studentDAO.update(student);
		System.out.println("This is the student updated: " + student.toString());
	}

	private void getStudentByLastName(StudentDAO studentDAO) {
		List<Student> studentsFounded = studentDAO.findByLastName("Alegre");
		for (Student student : studentsFounded) {
			System.out.println(student.toString());
		}
	}

	private void getAllByQuery(StudentDAO studentDAO) {
		List<Student> studentsFounded = studentDAO.findAll();
		for (Student student : studentsFounded) {
			System.out.println(student.toString());
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		Student student = new Student("Calamardo", "Tentáculos", "cala100@outlook.com");
		studentDAO.save(student);
		int studentCreatedID = student.getId();
		Student studentFounded = studentDAO.findByID(studentCreatedID);
		System.out.println("This is the student created" + studentFounded.toString());
	}

	private void createStudent(StudentDAO studentDAO) {

		Student student = new Student("Andres", "Doctor", "rosa100@gmail.com");
		System.out.println("Other student was make" + student.toString());

		studentDAO.save(student);
		System.out.println("The student: " + student.toString() + ", was registered");

	}

}
