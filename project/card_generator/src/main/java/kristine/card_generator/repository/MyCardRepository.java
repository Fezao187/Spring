package kristine.card_generator.repository;

import kristine.card_generator.models.entities.MyCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyCardRepository extends JpaRepository<MyCard, Integer> {
    List<MyCard> findByUserId(Integer userId);
}
