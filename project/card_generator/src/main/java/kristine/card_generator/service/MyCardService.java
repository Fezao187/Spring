package kristine.card_generator.service;

import kristine.card_generator.models.entities.MyCard;
import kristine.card_generator.models.entities.User;
import kristine.card_generator.models.responses.MyCardResponse;
import kristine.card_generator.repository.MyCardRepository;
import kristine.card_generator.repository.UserRepository;
import kristine.card_generator.security.service.JwtService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCardService {
    private final UserRepository userRepository;
    private final MyCardRepository myCardRepository;
    private final JwtService jwtService;

    public MyCardService(UserRepository userRepository, MyCardRepository myCardRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.myCardRepository = myCardRepository;
        this.jwtService = jwtService;
    }

    public MyCardResponse createCard(String token, MyCard request) {
        MyCard myCard = new MyCard();
        String username = jwtService.extractUsername(token);

        User user = userRepository.findByUsername(username).orElseThrow();

        myCard.setName(request.getName());
        myCard.setCardNumber(request.getCardNumber());
        myCard.setCvv(request.getCvv());
        myCard.setExpiryDate(request.getExpiryDate());
        myCard.setUser(user);

        myCardRepository.save(myCard);

        return new MyCardResponse(myCard);
    }

    public MyCardResponse getMyCard(Integer cardId) {
        MyCard myCard = myCardRepository.findById(cardId).orElseThrow();
        return new MyCardResponse(myCard);
    }

    public List<MyCard> getMyCards(String token) {
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow();
        Integer userId = user.getId();
        List<MyCard> myCards = myCardRepository.findByUserId(userId);

        return myCards;
    }

    public MyCardResponse updateCard(Integer cardId, MyCard request) {
        MyCard myCard = myCardRepository.findById(cardId).orElseThrow();
        myCard.setName(request.getName());
        myCard.setCardNumber(request.getCardNumber());
        myCard.setCvv(request.getCvv());
        myCard.setExpiryDate(request.getExpiryDate());
        myCardRepository.save(myCard);

        return new MyCardResponse(myCard);
    }

    public MyCardResponse deleteCard(Integer cardId) {
        MyCard myCard = myCardRepository.findById(cardId).orElseThrow();
        myCardRepository.deleteById(cardId);
        return new MyCardResponse(myCard);
    }
}
