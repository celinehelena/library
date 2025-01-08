package com.library.library.exceptions;

public class LimiteDeReservasException extends RuntimeException {
    public LimiteDeReservasException(String mensagem) {
        super(mensagem); // Define a mensagem do erro
    }
}
