package com.massimilianodj.dj_events_api.exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(Long id){
        super("Event with id "+id+" not found.");
    }
}
