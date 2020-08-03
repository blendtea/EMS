package core.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	//Account Details
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "user_name")
    @Length(min = 3, max =3, message = "*社員IDが正しくありません。例)001")
    @NotEmpty(message = "*社員IDを入力してください")
    private String userName;
    @Column(name = "password")
    @Length(min = 5, message = "*パスワードは最低5文字以上入力してください")
    @NotEmpty(message = "*パスワードを入力してください")
    private String password;
    @Column(name = "first_name")
    @NotEmpty(message = "*名を入力してください")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "*姓を入力してください")
    private String lastName;
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
	
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}

/*
 * アカウント情報モデル(Table = users)
 */
