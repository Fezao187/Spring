package kristine.card_generator.service;

import kristine.card_generator.models.User;
import kristine.card_generator.models.VirtualCard;
import kristine.card_generator.models.VirtualCardResponse;
import kristine.card_generator.repository.UserRepository;
import kristine.card_generator.repository.VirtualCardRepository;
import kristine.card_generator.security.service.JwtService;
import kristine.card_generator.tools.utils.GenerateCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirtualCardService {
    private final UserRepository userRepository;
    private final VirtualCardRepository virtualCardRepository;
    private final JwtService jwtService;
    private final GenerateCard generateCard;

    public VirtualCardService(UserRepository userRepository, VirtualCardRepository virtualCardRepository, JwtService jwtService, GenerateCard generateCard) {
        this.userRepository = userRepository;
        this.virtualCardRepository = virtualCardRepository;
        this.jwtService = jwtService;
        this.generateCard = generateCard;
    }

    public VirtualCardResponse createCard(String token){
        VirtualCard virtualCard = new VirtualCard();
        String username = jwtService.extractUsername(token);

        User user = userRepository.findByUsername(username).orElseThrow();

        virtualCard.setName(user.getName());
        virtualCard.setCardNumber(generateCard.genCardNum(16));
        virtualCard.setCvv(generateCard.genCvvNum(3));
        virtualCard.setExpiryDate(generateCard.genDate());
        virtualCard.setUser(user);

        virtualCardRepository.save(virtualCard);

        return new VirtualCardResponse(virtualCard);
    }

   public VirtualCardResponse getCard(Integer cardId){
        VirtualCard virtualCard = virtualCardRepository.findById(cardId).orElseThrow();
        return new VirtualCardResponse(virtualCard);
   }

   public List<VirtualCard> getMyVirtualCards(String token){
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow();
        Integer userId = user.getId();
        List<VirtualCard> virtualCards = virtualCardRepository.findByUserId(userId);

        return virtualCards;
   }

   public VirtualCardResponse deleteVirtualCard(Integer cardId){
        VirtualCard virtualCard = virtualCardRepository.findById(cardId).orElseThrow();
        virtualCardRepository.deleteById(cardId);

        return new VirtualCardResponse(virtualCard);
   }
}
