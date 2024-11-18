package kristine.card_generator.repository;

import kristine.card_generator.models.entities.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirtualCardRepository extends JpaRepository<VirtualCard, Integer> {
    List<VirtualCard> findByUserId(Integer userId);
}
