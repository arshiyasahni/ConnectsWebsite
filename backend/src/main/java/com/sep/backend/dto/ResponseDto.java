package com.sep.backend.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * The type Response dto.
 *
 * @param <T> the type parameter
 */
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponseDto<T> {

private T data;
private List<String> messages;
private List<String> errors;

/**
 * Instantiates a new Response dto.
 *
 * @param data the data
 */
public ResponseDto(T data) {
     this.data = data;
}

/**
 * Instantiates a new Response dto.
 *
 * @param errors the errors
 */
public ResponseDto(List<String> errors) {
     this.errors = Collections.unmodifiableList(errors);
     this.data = null;
}

/**
 * Instantiates a new Response dto.
 *
 * @param data     the data
 * @param messages the messages
 */
public ResponseDto(T data, List<String> messages) {
     this.data = data;
     this.setMessages(messages);
}

/**
 * Instantiates a new Response dto.
 *
 * @param message the message
 * @param errors  the errors
 */
public ResponseDto(List<String> message, List<String> errors){
     this.messages = message;
     this.errors = errors;
}

}
