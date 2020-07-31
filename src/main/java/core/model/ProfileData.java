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
@Table(name = "profile")
public class ProfileData {
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="name")
	private String fullName;

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
 * プロフィール情報モデル(Table = profile)
 * (未使用クラス(未実装)
 */
