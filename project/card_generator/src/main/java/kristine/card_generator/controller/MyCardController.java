package kristine.card_generator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kristine.card_generator.models.entities.MyCard;
import kristine.card_generator.models.responses.MyCardResponse;
import kristine.card_generator.service.MyCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MyCard",
description = "My card management APIs")
@RestController
@RequestMapping("/my/card")
public class MyCardController {
    private final MyCardService myCardService;

    public MyCardController(MyCardService myCardService) {
        this.myCardService = myCardService;
    }

    @Operation(
            summary = "Add card",
            description = "Add card by entering all fields",
            tags = { "MyCards", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MyCard.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/create")
    public ResponseEntity<MyCardResponse> createCard(
            @RequestHeader(name="Authorization") String token,
            @RequestBody MyCard myCard
            ){
        return new ResponseEntity<>(myCardService.createCard(token, myCard), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get list of cards",
            description = "Get a list of my cards by checking token and find all cards in database",
            tags = { "MyCards", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MyCard.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/all")
    public ResponseEntity<List<MyCard>> getAllCards(
            @RequestHeader(name="Authorization") String token
    ) {
        return ResponseEntity.ok(myCardService.getMyCards(token));
    }

    @Operation(
            summary = "Get card by id",
            description = "Get card by specifying id",
            tags = { "MyCards", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MyCard.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<MyCardResponse> getCardById(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(myCardService.getMyCard(id));
    }

    @Operation(
            summary = "Edit card",
            description = "Edit card by specifying id and entering all fields",
            tags = { "MyCards", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MyCard.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<MyCardResponse> updateCard(
            @PathVariable("id") Integer id,
            @RequestBody MyCard myCard
    ){
        return ResponseEntity.ok(myCardService.updateCard(id, myCard));
    }

    @Operation(
            summary = "Delete card",
            description = "Delete card by using specified card id",
            tags = { "MyCards", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MyCard.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<MyCardResponse> deleteCard(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(myCardService.deleteCard(id));
    }
}
