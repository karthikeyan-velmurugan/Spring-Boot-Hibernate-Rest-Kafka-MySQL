/**
 * 
 */
package com.cyber.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@JsonIgnoreProperties(value = {"postedDate"})
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="job_id",nullable=false, unique=true)
	public int jobId;

	@Column(name="job_title")
	public String jobTitle;

	@Column(name="job_description")
	public String jobDescription;

	@Column(name="country")
	public String country;

	@Column(name="state")
	public String state;

	@Column(name="availability")
	public String availability; // HOURLY, PART-TIME, FULL-TIME

	@Column(name="reply_rate")
	public int replyRate;

	@Column(name="pay_rate")
	public int payRate;

	@Column(name="experience")
	public int experience;

	@Column(name="skills")
	public String skills; // JAVA, MOBILE, UI

	@Column(name="language")
	public String language; // ENGLISH, CHINESE
	
	@Column(name="job_type")
	public String jobType;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="IST")
	@Column(name="posted_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	public Date postedDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id", nullable=false)
	public UserInfo userInfo;

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

	public int getReplyRate() {
		return replyRate;
	}

	public void setReplyRate(int replyRate) {
		this.replyRate = replyRate;
	}

	public int getPayRate() {
		return payRate;
	}

	public void setPayRate(int payRate) {
		this.payRate = payRate;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobTitle=" + jobTitle + ", jobDescription=" + jobDescription + ", country="
				+ country + ", state=" + state + ", availability=" + availability + ", replyRate=" + replyRate
				+ ", payRate=" + payRate + ", experience=" + experience + ", skills=" + skills + ", language="
				+ language + ", jobType=" + jobType + ", postedDate=" + postedDate + ", userInfo=" + userInfo + "]";
	}
}