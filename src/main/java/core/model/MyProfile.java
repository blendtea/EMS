package core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MyProfile")
public class MyProfile {
	//Account Details
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    // Profile Editor
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

/*
 * アカウント情報モデル(Table = users)
 */
