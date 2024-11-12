package kristine.card_generator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VirtualCard {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer cardNumber;
    private Integer expiryDate;
    private Integer cvv;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
