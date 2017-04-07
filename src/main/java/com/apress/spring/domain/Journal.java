package com.apress.spring.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "formatter")
@ToString(exclude = "formatter")
public class Journal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	private Long id;

	@Getter @Setter
	private String title;

	@JsonIgnore
	@Getter	@Setter
	private LocalDate created;

	@Getter @Setter
	private String summary;

	@Transient
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	public Journal(String title, String summary, String date) {
		this.title = title;
		this.summary = summary;
		this.created = LocalDate.parse(date, formatter);
	}

	public String getCreatedAsShort() {
		return this.created.format(formatter);
	}
}
