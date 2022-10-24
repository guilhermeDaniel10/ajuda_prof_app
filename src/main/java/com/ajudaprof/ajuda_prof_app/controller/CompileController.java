package com.ajudaprof.ajuda_prof_app.controller;

import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;


@RestController
@RequestMapping("/api/test-heroku")
public class CompileController {

    @GetMapping("/hello")
    public ResponseEntity<JSONObject> testDeploy() {
        try {
            String message;

            JSONObject obj=new JSONObject();
            obj.put("test","hello wordl");

            return new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            JSONObject obj=new JSONObject();
            obj.put("erro","erro");
            return new ResponseEntity<JSONObject>(obj,HttpStatus.BAD_REQUEST);
        }
    }
}
