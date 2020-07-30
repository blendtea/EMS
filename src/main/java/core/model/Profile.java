package core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="profile")
public class Profile implements Serializable {
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="sex")
	private String sex;

	@Column(name="assigned")
	private String assigned;
	
	@Column(name="birth")
	private String birth;
	
	@Column(name="school")
	private String school;
	
	@Column(name="hobby")
	private String hobby;
	
	@Column(name="msg")
	private String shortMessage;
}
