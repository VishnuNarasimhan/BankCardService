package com.bank.cardservice.controller;

import com.bank.cardservice.constants.CardConstants;
import com.bank.cardservice.dto.CardDto;
import com.bank.cardservice.dto.ResponseDto;
import com.bank.cardservice.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CardController {
    private ICardService iCardService;

    /**
     *
     * @param mobileNumber - Mobile number of the customer
     * @return ResponseDto
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber) {
        iCardService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    /**
     *
     * @param mobileNumber - Mobile number of the customer
     * @return ResponseDto
     */
    @GetMapping("/fetch")
    public ResponseEntity<CardDto> getCard(@RequestParam String mobileNumber) {
        CardDto cardDto = iCardService.fetchCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardDto);
    }

    /**
     *
     * @param cardDto - CustomerDto Object
     * @return boolean indicating if the update of Card details is successful or not
     */
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@RequestBody CardDto cardDto) {
        boolean isUpdated = iCardService.updateCard(cardDto);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber) {
        boolean isDeleted = iCardService.deleteCard(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
    }

}
