package kristine.card_generator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyCard{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private BigInteger cardNumber;
    private String expiryDate;
    private Integer cvv;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
