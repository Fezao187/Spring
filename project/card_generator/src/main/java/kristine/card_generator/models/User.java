package kristine.card_generator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<MyCard> myCards;
    @OneToMany(mappedBy = "user")
    private List<VirtualCard> virtualCards;
}
