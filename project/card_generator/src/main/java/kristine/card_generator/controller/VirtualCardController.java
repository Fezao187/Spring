package kristine.card_generator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kristine.card_generator.models.entities.VirtualCard;
import kristine.card_generator.models.responses.VirtualCardResponse;
import kristine.card_generator.service.VirtualCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MyVirtualCard",
description = "Manages virtual card APIs")
@RestController
@RequestMapping("/virtual/card")
public class VirtualCardController {
    private final VirtualCardService virtualCardService;

    public VirtualCardController(VirtualCardService virtualCardService) {
        this.virtualCardService = virtualCardService;
    }

    @Operation(
            summary = "Create card",
            description = "Creates a card by entering the Bearer token and the name of the card. It will generate a random card",
            tags = { "VirtualCards", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = VirtualCard.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/create")
    public ResponseEntity<VirtualCardResponse> createCard(
            @RequestHeader(name="Authorization") String token,
            @RequestBody VirtualCard virtualCard) {
        return ResponseEntity.ok(virtualCardService.createCard(token, virtualCard));
    }

    @Operation(
            summary = "Get card virtual by id",
            description = "Gets card by specifying card id.",
            tags = { "VirtualCards", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = VirtualCard.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<VirtualCardResponse> getCard(
            @PathVariable("id") Integer id){
        return ResponseEntity.ok(virtualCardService.getCard(id));
    }

    @Operation(
            summary = "Get a list of my virtual cards",
            description = "Gets card by using the token to check for username in database.",
            tags = { "VirtualCards", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = VirtualCard.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/all")
    public ResponseEntity<List<VirtualCard>> getAllCards(
            @RequestHeader (name="Authorization") String token
    ){
            return ResponseEntity.ok(virtualCardService.getMyVirtualCards(token));
    }

    @Operation(
            summary = "Delete card by ID",
            description = "Deletes card by using the specified id",
            tags = { "VirtualCards", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = VirtualCard.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<VirtualCardResponse> deleteCard(
            @PathVariable("id") Integer id){
        return ResponseEntity.ok(virtualCardService.deleteVirtualCard(id));
    }
}
