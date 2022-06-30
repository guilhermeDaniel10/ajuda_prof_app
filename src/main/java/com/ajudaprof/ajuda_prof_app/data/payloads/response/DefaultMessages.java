package com.ajudaprof.ajuda_prof_app.data.payloads.response;

public enum DefaultMessages {

    SUCESSO_APAGADO("Apagado com sucesso");

    String messages;
    DefaultMessages(String messages){
        this.messages = messages;
    }

    public String getMessage(){
        return messages;
    }

    public MessageResponse getMessageAsResponse() {
        return new MessageResponse(messages);
    }
}
