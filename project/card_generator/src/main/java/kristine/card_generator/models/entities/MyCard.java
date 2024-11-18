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
public class MyCard{
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private BigInteger cardNumber;
    @NotEmpty
    private String expiryDate;
    @NotEmpty
    private Integer cvv;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
