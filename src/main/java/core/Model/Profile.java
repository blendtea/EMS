package core.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Profile {
	//Account Details
    @Id
    @Column(name = "profile_id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name="sex")
    @Pattern(regexp = "男性|女性", message="選択してください")
	private String sex;
	@Column(name="assigned")
	@NotEmpty(message = "*必須")
	@Length(max = 20, message = "*最大20文字まで")
	private String assigned;
	@Column(name="birth")
	@NotEmpty(message = "*必須")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private String birth;
	@Column(name="school")
	@Length(max = 30, message = "最大30文字まで")
	private String school;
	@Column(name="hobby")
	@NotEmpty(message = "*必須")
	@Length(max = 30, message = "*最大30文字まで")
	private String hobby;
	@Column(name="town")
	@NotEmpty(message = "*必須")
	@Length(max = 30, message = "*最大30文字まで")
	private String town;
	@Column(name="msg")
	@NotEmpty(message = "*必須")
	@Length(max = 40, message = "*最大40文字まで")
	private String msg;
}
