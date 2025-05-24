package com.example.card_service.controller;

import com.example.card_service.api.ApiRespon;
import com.example.card_service.request.AddCardRequest;
import com.example.card_service.request.ApplyCardRequest;
import com.example.card_service.request.CardMiniUpdateRequest;
import com.example.card_service.respon.*;
import com.example.card_service.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card-service/api/v1")
public class HomeController {

    @Autowired
    CardService cardService;

    @RequestMapping("/card-user")
    public ApiRespon<CardsRespon> get_card(HttpServletRequest _request){

        return cardService.get_card_service(_request);
    }

    @DeleteMapping("/card-user/{_id_card_mini}")
    public ApiRespon<CardMiniDeleteRespon> delete_card_mini(@PathVariable("_id_card_mini") String _id_card_mini,HttpServletRequest _request){

        return cardService.delete_card_mini_respon(_id_card_mini, _request);
    }

    @PutMapping("/card-user")
    public ApiRespon<CardMiniUpdateRespon> update_card_mini(@RequestBody @Valid CardMiniUpdateRequest _card_mini_update_request){

        return cardService.update_card_mini_request(_card_mini_update_request);
    }

    @PutMapping("/card-user-apply")
    public ApiRespon<ApplyCardRespon> apply_card(@RequestBody @Valid ApplyCardRequest _apply_card_request, HttpServletRequest _request){

        return cardService.apply_card_service(_apply_card_request,_request);
    }

    @PostMapping("/add-card")
    public ApiRespon<AddCardRespon> add_card(@RequestBody @Valid AddCardRequest _add_card_request, HttpServletRequest _request){

        return cardService.add_card_service(_add_card_request,_request);
    }
}
