package com.example.devicenote.exception;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(RuntimeException.class)
 public Map<String,String> handle(RuntimeException ex){
  Map<String,String> map = new HashMap<>();
  map.put("error", ex.getMessage());
  return map;
 }
}
