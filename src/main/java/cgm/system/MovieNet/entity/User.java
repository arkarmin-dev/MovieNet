package cgm.system.MovieNet.entity;


import cgm.system.MovieNet.form.UserForm;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable = false, name = "role_id")
    private Role role;

    public User(String name, String email, String password, Role role){
        this.userName = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(UserForm userForm){
        this.id = userForm.getId();
        this.userName = userForm.getUserName();
        this.email = userForm.getEmail();
        this.password = userForm.getPassword();
    }
}
