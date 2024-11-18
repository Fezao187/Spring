package kristine.card_generator.controller;

import kristine.card_generator.models.entities.VirtualCard;
import kristine.card_generator.models.responses.VirtualCardResponse;
import kristine.card_generator.service.VirtualCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/virtual/card")
public class VirtualCardController {
    private final VirtualCardService virtualCardService;

    public VirtualCardController(VirtualCardService virtualCardService) {
        this.virtualCardService = virtualCardService;
    }

    @GetMapping("/create")
    public ResponseEntity<VirtualCardResponse> createCard(
            @RequestHeader(name="Authorization") String token){
        return ResponseEntity.ok(virtualCardService.createCard(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VirtualCardResponse> getCard(
            @PathVariable("id") Integer id){
        return ResponseEntity.ok(virtualCardService.getCard(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VirtualCard>> getAllCards(
            @RequestHeader (name="Authorization") String token
    ){
        return ResponseEntity.ok(virtualCardService.getMyVirtualCards(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VirtualCardResponse> deleteCard(
            @PathVariable("id") Integer id){
        return ResponseEntity.ok(virtualCardService.deleteVirtualCard(id));
    }
}
