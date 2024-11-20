package com.ayeldev.Student_Management_API.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_student")
public class Student {

	public Student() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "middlename")
	private String middlename;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "sex")
	private char sex;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_number")
	private String phone_number;
	
	@Column(name = "date_of_birth")
	private Date date_of_birth;
	
	@Column(name= "date_enrolled")
	private Date date_enrolled;
	
	@Column(name = "course")
	private String course;

	public Student(long id, String firstname, String middlename, String lastname, char sex, String email,
			String phone_number, Date date_of_birth, Date date_enrolled, String course) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.sex = sex;
		this.email = email;
		this.phone_number = phone_number;
		this.date_of_birth = date_of_birth;
		this.date_enrolled = date_enrolled;
		this.course = course;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public Date getDate_enrolled() {
		return date_enrolled;
	}

	public void setDate_enrolled(Date date_enrolled) {
		this.date_enrolled = date_enrolled;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", middlename=" + middlename + ", lastname="
				+ lastname + ", sex=" + sex + ", email=" + email + ", phone_number=" + phone_number + ", date_of_birth="
				+ date_of_birth + ", date_enrolled=" + date_enrolled + ", course=" + course + "]";
	}
	
}
