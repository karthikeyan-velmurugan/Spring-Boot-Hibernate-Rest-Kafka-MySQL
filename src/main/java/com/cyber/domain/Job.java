/**
 * 
 */
package com.cyber.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Karthikeyan
 *
 */
@Entity
@Table(name="tbl_job")
@JsonIgnoreProperties(value = {"postedDate"}, allowGetters = true)
public class Job  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="job_id",nullable=false, unique=true)
	public int jobId;

	@Column(name="job_title")
	public String jobTitle;

	@Column(name="job_description")
	public String jobDescription;

	@Column(name="user_name")
	public String userName;

	@Column(name="country")
	public String country;

	@Column(name="state")
	public String state;

	@Column(name="availability")
	public String availability; // HOURLY, PART-TIME, FULL-TIME

	@Column(name="reply_rate")
	public String replyRate;

	@Column(name="pay_rate")
	public String payRate;

	@Column(name="experience")
	public String experience;

	@Column(name="skills")
	public String skills; // JAVA, MOBILE, UI

	@Column(name="language")
	public String language; // ENGLISH, CHINESE

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="IST")
	@Column(name="posted_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	public Date postedDate;

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getReplyRate() {
		return replyRate;
	}

	public void setReplyRate(String replyRate) {
		this.replyRate = replyRate;
	}

	public String getPayRate() {
		return payRate;
	}

	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobTitle=" + jobTitle + ", jobDescription=" + jobDescription + ", userName="
				+ userName + ", country=" + country + ", state=" + state + ", availability=" + availability
				+ ", replyRate=" + replyRate + ", payRate=" + payRate + ", experience=" + experience + ", skills="
				+ skills + ", language=" + language + ", postedDate=" + postedDate + "]";
	}    

}