package kristine.card_generator.service;

import kristine.card_generator.models.entities.User;
import kristine.card_generator.models.entities.VirtualCard;
import kristine.card_generator.models.responses.VirtualCardResponse;
import kristine.card_generator.repository.UserRepository;
import kristine.card_generator.repository.VirtualCardRepository;
import kristine.card_generator.security.service.JwtService;
import kristine.card_generator.tools.utils.GenerateCard;
import kristine.card_generator.tools.utils.SpliteToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirtualCardService {
    private final UserRepository userRepository;
    private final VirtualCardRepository virtualCardRepository;
    private final JwtService jwtService;
    private final GenerateCard generateCard;
    private final SpliteToken spliteToken;

    public VirtualCardService(UserRepository userRepository, VirtualCardRepository virtualCardRepository, JwtService jwtService, GenerateCard generateCard, SpliteToken spliteToken) {
        this.userRepository = userRepository;
        this.virtualCardRepository = virtualCardRepository;
        this.jwtService = jwtService;
        this.generateCard = generateCard;
        this.spliteToken = spliteToken;
    }

    public VirtualCardResponse createCard(String token, VirtualCard request) {
        if(token == null){
            return new VirtualCardResponse("You are not logged in, please login first!");
        }
        String filteredToken = spliteToken.split(token);
        VirtualCard virtualCard = new VirtualCard();
        String username = jwtService.extractUsername(filteredToken);

        User user = userRepository.findByUsername(username).orElseThrow();
        User newUser = new User(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
        try {
            if(request.getName()==null) {
                return new VirtualCardResponse("Please enter card name!");
            }

            virtualCard.setName(request.getName());
            virtualCard.setCardNumber(generateCard.genCardNum(16));
            virtualCard.setCvv(generateCard.genCvvNum(3));
            virtualCard.setExpiryDate(generateCard.genDate());
            virtualCard.setUser(newUser);
        }catch (Exception e) {
            return new VirtualCardResponse("An error occured! Please try again!");
        }
        virtualCardRepository.save(virtualCard);

        return new VirtualCardResponse(virtualCard, "Virtual Card Created!");
    }

   public VirtualCardResponse getCard(Integer cardId){
        VirtualCard virtualCard = virtualCardRepository.findById(cardId).orElseThrow();
        if(virtualCard == null){
            return new VirtualCardResponse("Virtual Card Not Found!");
        }
        VirtualCard newVirtualCard = new VirtualCard(
                virtualCard.getId(),
                virtualCard.getName(),
                virtualCard.getCardNumber(),
                virtualCard.getCvv(),
                virtualCard.getExpiryDate()
        );
        return new VirtualCardResponse(newVirtualCard);
   }

   public List<VirtualCard> getMyVirtualCards(String token){
       String filteredToken = spliteToken.split(token);
        String username = jwtService.extractUsername(filteredToken);
        User user = userRepository.findByUsername(username).orElseThrow();

        Integer userId = user.getId();
        List<VirtualCard> virtualCards = virtualCardRepository.findByUserId(userId);
        return virtualCards;
   }

   public VirtualCardResponse deleteVirtualCard(Integer cardId){
       VirtualCard virtualCard = virtualCardRepository.findById(cardId).orElseThrow();
       if (cardId == null){
           return new VirtualCardResponse("Please enter valid card ID!");
       }
       if(virtualCard == null){
           return new VirtualCardResponse("Virtual Card Not Found! Please try again!");
       }
       try {
           virtualCardRepository.deleteById(cardId);
       }catch (Exception e) {
           return new VirtualCardResponse("An error occured! Please try again!");
       }
       VirtualCard newVirtualCard = new VirtualCard(
               virtualCard.getId(),
               virtualCard.getName(),
               virtualCard.getCardNumber(),
               virtualCard.getCvv(),
               virtualCard.getExpiryDate()
       );
       return new VirtualCardResponse(newVirtualCard, "Card Deleted successfully!");
   }
}
