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
			createManyStudent(studentDAO);
			// readStudent(studentDAO);
			// getAllByQuery(studentDAO);
			// getStudentByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudent(studentDAO);
		};
	}

	private void createManyStudent(StudentDAO studentDAO) {

		Student student01 = new Student("Rogelio", "Sanchez", "Rogelio01@gmail.com");
		Student student02 = new Student("Andres", "Lopez", "Andres01@gmail.com");
		Student student03 = new Student("Paula", "Rodriguez", "Paula01@gmail.com");

		studentDAO.save(student01);
		studentDAO.save(student02);
		studentDAO.save(student03);
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		int studentDeleted = studentDAO.deleteAll();
		System.err.println("[Student deleted] = " + studentDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int idToDelete = 4;
		studentDAO.deleteByID(idToDelete);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int id = 4;
		Student student = studentDAO.findByID(id);
		student.setFirst_name("Scooby");
		student.setLast_name("Doo");
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
