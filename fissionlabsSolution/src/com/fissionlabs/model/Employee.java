package com.fissionlabs.model;

public class Employee {

	// this is an extra field, not using anywhere, can remove also.
	private Integer id;
	
	private String firstName;
	private String lastName;
	private Integer experienceInMonth;
	private Integer ageInYear;
	private String organization;

	public Employee() {
	}

	public Employee(String firstName, String lastName,
			Integer experienceInMonth, Integer ageInYear, String organization) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.experienceInMonth = experienceInMonth;
		this.ageInYear = ageInYear;
		this.organization = organization;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getExperienceInMonth() {
		return experienceInMonth;
	}

	public void setExperienceInMonth(Integer experienceInMonth) {
		this.experienceInMonth = experienceInMonth;
	}

	public Integer getAgeInYear() {
		return ageInYear;
	}

	public void setAgeInYear(Integer ageInYear) {
		this.ageInYear = ageInYear;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", experienceInMonth="
				+ experienceInMonth + ", ageInYear=" + ageInYear
				+ ", organization=" + organization + "]";
	}
}
