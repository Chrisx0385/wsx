package de.cgz.data.contact;

import java.util.Calendar;

import de.cgz.data.types.Gender;
import de.cgz.data.types.collection.container.DataContainer;
import de.cgz.data.types.time.Date;


public class PersonData extends AbstractContactData implements NamingData {
	
	public static final String[] properties = {"businessName", "firstName", "lastName", "birthDay", "gender"};
	
	private String businessName;
	private String firstName;
	private String lastName;	
	
	private Calendar birthDay;
	
	private Gender gender;
	
	private DataContainer<Address> addresses;
	
	private DataContainer<Notice> notices;
	
	private DataContainer<Phone> phones;
	
	private DataContainer<Email> emails;
	
	private DataContainer<Picture> pictures;

	
	public String getBusinessName() {
		return businessName;
	}

	
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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

	
	public Calendar getBirthDay() {
		return birthDay;
	}

	
	public void setBirthDay(Calendar birthDay) {
		this.birthDay = birthDay;
	}

	
	public Gender getGender() {
		return gender;
	}

	
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	
	public DataContainer<Address> getAddresses() {
		return addresses;
	}

	
	public void setAddresses(DataContainer<Address> addresses) {
		this.addresses = addresses;
	}

	
	public DataContainer<Notice> getNotices() {
		return notices;
	}

	
	public void setNotices(DataContainer<Notice> notices) {
		this.notices = notices;
	}

	
	public DataContainer<Phone> getPhones() {
		return phones;
	}

	
	public void setPhones(DataContainer<Phone> phones) {
		this.phones = phones;
	}

	
	public DataContainer<Email> getEmails() {
		return emails;
	}

	
	public void setEmails(DataContainer<Email> emails) {
		this.emails = emails;
	}

	
	public DataContainer<Picture> getPictures() {
		return pictures;
	}

	
	public void setPictures(DataContainer<Picture> pictures) {
		this.pictures = pictures;
	}	
	
	
}
