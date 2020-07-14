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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "user_name")
    @Length(min = 5, message = "* 社員IDは最低5文字以上入力してください")
    @NotEmpty(message = "* 社員IDを記入してください")
    private String userName;
//    @Column(name = "email")
//    @Email(message = "* メールアドレスが不正です")
//    @NotEmpty(message = "* メールアドレスを記入してください")
//    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "* パスワードは最低５文字以上入力してください")
    @NotEmpty(message = "* パスワードを記入してください")
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "* 名前を入力してください")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "* 苗字を入力してください")
    private String lastName;
    @Column(name = "active")
    private Boolean active;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
