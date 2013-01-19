package de.cgz.data.contact;

import java.util.Calendar;

import de.cgz.data.types.Gender;
import de.cgz.data.types.collection.container.DataContainer;
import de.cgz.data.types.collection.container.ListDataContainer;

public class PersonData extends AbstractContactData implements NamingData {

	

	private String businessName;
	private String firstName;
	private String lastName;

	private Calendar birthDay;

	private Gender gender;

	private ListDataContainer<GermanAddress> addresses;

	private ListDataContainer<Notice> notices;

	private ListDataContainer<Phone> phones;

	private ListDataContainer<Email> emails;

	private ListDataContainer<Picture> pictures;

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

	public ListDataContainer<GermanAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(ListDataContainer<GermanAddress> addresses) {
		this.addresses = addresses;
	}

	public ListDataContainer<Notice> getNotices() {
		return notices;
	}

	public void setNotices(ListDataContainer<Notice> notices) {
		this.notices = notices;
	}

	public ListDataContainer<Phone> getPhones() {
		return phones;
	}

	public void setPhones(ListDataContainer<Phone> phones) {
		this.phones = phones;
	}

	public DataContainer<Email> getEmails() {
		return emails;
	}

	public void setEmails(ListDataContainer<Email> emails) {
		this.emails = emails;
	}

	public ListDataContainer<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(ListDataContainer<Picture> pictures) {
		this.pictures = pictures;
	}

}
