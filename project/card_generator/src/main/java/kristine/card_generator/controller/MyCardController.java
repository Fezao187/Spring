package kristine.card_generator.controller;

import kristine.card_generator.models.entities.MyCard;
import kristine.card_generator.models.responses.MyCardResponse;
import kristine.card_generator.service.MyCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my/card")
public class MyCardController {
    private final MyCardService myCardService;

    public MyCardController(MyCardService myCardService) {
        this.myCardService = myCardService;
    }

    @PostMapping("/create")
    public ResponseEntity<MyCardResponse> createCard(
            @RequestHeader(name="Authorization") String token,
            @RequestBody MyCard myCard
            ){
        return new ResponseEntity<>(myCardService.createCard(token, myCard), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MyCard>> getAllCards(
            @RequestHeader(name="Authorization") String token
    ) {
        return ResponseEntity.ok(myCardService.getMyCards(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyCardResponse> getCardById(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(myCardService.getMyCard(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyCardResponse> updateCard(
            @PathVariable("id") Integer id,
            @RequestBody MyCard myCard
    ){
        return ResponseEntity.ok(myCardService.updateCard(id, myCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MyCardResponse> deleteCard(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(myCardService.deleteCard(id));
    }
}
