package kristine.card_generator.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class VirtualCard {
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

    public VirtualCard(Integer id,String name, BigInteger cardNumber,Integer cvv, String expiryDate) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
}
