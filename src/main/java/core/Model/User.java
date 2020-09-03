package core.Model;

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
    private Long id;
    @Column(name = "user_name")
    @Length(min = 3, max =3, message = "*必須項目")
    private String userName;
    @Column(name = "password")
    @Length(min = 5, message = "*最低5文字以上")
//  @NotEmpty(message = "")
    private String password;
    @Column(name = "first_name")
    @NotEmpty(message = "*必須項目")
    @Length(max = 12, message = "*最大12文字まで")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "*必須項目")
    @Length(max = 12, message = "*最大12文字まで")
    private String lastName;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
