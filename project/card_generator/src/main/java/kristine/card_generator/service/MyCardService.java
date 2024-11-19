package kristine.card_generator.service;

import kristine.card_generator.models.entities.MyCard;
import kristine.card_generator.models.entities.User;
import kristine.card_generator.models.responses.MyCardResponse;
import kristine.card_generator.repository.MyCardRepository;
import kristine.card_generator.repository.UserRepository;
import kristine.card_generator.security.service.JwtService;
import kristine.card_generator.tools.utils.SpliteToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCardService {
    private final UserRepository userRepository;
    private final MyCardRepository myCardRepository;
    private final JwtService jwtService;
    private final SpliteToken spliteToken;

    public MyCardService(UserRepository userRepository, MyCardRepository myCardRepository, JwtService jwtService, SpliteToken spliteToken) {
        this.userRepository = userRepository;
        this.myCardRepository = myCardRepository;
        this.jwtService = jwtService;
        this.spliteToken = spliteToken;
    }

    public MyCardResponse createCard(String token, MyCard request) {
        if(token == null)
        {
            return new MyCardResponse("You are not logged in, please login first!");
        }
        if(request.getName() == null || request.getName().isEmpty())
        {
            return new MyCardResponse("Please enter your card name!");
        } else if (request.getCardNumber() == null) {
            return new MyCardResponse("Please enter your card number!");
        } else if (request.getCvv() == null) {
            return new MyCardResponse("Please enter your cvv!");
        } else if (request.getExpiryDate() == null) {
            return new MyCardResponse("Please enter your expiry date!");
        }
        MyCard myCard = new MyCard();
        String filteredToken = spliteToken.split(token);
        String username = jwtService.extractUsername(filteredToken);

        User user = userRepository.findByUsername(username).orElseThrow();
        User newUser = new User(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
        try {
            myCard.setName(request.getName());
            myCard.setCardNumber(request.getCardNumber());
            myCard.setCvv(request.getCvv());
            myCard.setExpiryDate(request.getExpiryDate());
            myCard.setUser(newUser);
            myCardRepository.save(myCard);
        }catch (Exception e){
            return new MyCardResponse("Something went wrong!");
        }

        return new MyCardResponse(myCard, "Card created successfully!");
    }

    public MyCardResponse getMyCard(Integer cardId) {
        if(cardId == null){
            return new MyCardResponse("Please enter card ID");
        }
        MyCard myCard = myCardRepository.findById(cardId).orElseThrow();
        if(myCard == null){
            return new MyCardResponse("Card not found! Please try again!");
        }

        MyCard newMyCard = new MyCard(
                myCard.getId(),
                myCard.getName(),
                myCard.getCardNumber(),
                myCard.getCvv(),
                myCard.getExpiryDate()
        );

        return new MyCardResponse(newMyCard);
    }

    public List<MyCard> getMyCards(String token) {
        String filteredToken = spliteToken.split(token);
        String username = jwtService.extractUsername(filteredToken);
        User user = userRepository.findByUsername(username).orElseThrow();
        Integer userId = user.getId();
        List<MyCard> myCards = myCardRepository.findByUserId(userId);
        return myCards;
    }

    public MyCardResponse updateCard(Integer cardId, MyCard request) {
        if(cardId == null){
            return new MyCardResponse("Please enter card ID");
        }
        MyCard myCard = myCardRepository.findById(cardId).orElseThrow();
        if(myCard == null){
            return new MyCardResponse("Card not found! Please try again! Or enter valid card ID");
        }
        try {
            myCard.setName(request.getName());
            myCard.setCardNumber(request.getCardNumber());
            myCard.setCvv(request.getCvv());
            myCard.setExpiryDate(request.getExpiryDate());
            myCardRepository.save(myCard);
        }catch (Exception e){
            return new MyCardResponse("Something went wrong!");
        }
        MyCard newMyCard = new MyCard(
                myCard.getId(),
                myCard.getName(),
                myCard.getCardNumber(),
                myCard.getCvv(),
                myCard.getExpiryDate()
        );
        return new MyCardResponse(newMyCard, "Card updated successfully!");
    }

    public MyCardResponse deleteCard(Integer cardId) {
        if(cardId == null){
            return new MyCardResponse("Please enter card ID");
        }
        MyCard myCard = myCardRepository.findById(cardId).orElseThrow();
        if(myCard == null){
            return new MyCardResponse("Card not found! Please try again!");
        }
        try {
            myCardRepository.deleteById(cardId);
        }catch (Exception e){
            return new MyCardResponse("Something went wrong!");
        }
        MyCard newMyCard = new MyCard(
                myCard.getId(),
                myCard.getName(),
                myCard.getCardNumber(),
                myCard.getCvv(),
                myCard.getExpiryDate()
        );
        return new MyCardResponse(newMyCard, "Card deleted successfully!");
    }
}
