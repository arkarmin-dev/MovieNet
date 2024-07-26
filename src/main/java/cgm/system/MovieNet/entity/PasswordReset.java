package cgm.system.MovieNet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PasswordReset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(nullable = false)
    private String email;*/

    @Column(nullable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

/*
    public PasswordReset(String email,String token,User user,LocalDateTime expiryDate){
        this.email = email;
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }
*/
    public PasswordReset(){

    }
    public PasswordReset(User user,String token,LocalDateTime expiryDate){
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public boolean isValid() {
        return LocalDateTime.now().isBefore(expiryDate);
    }
}
