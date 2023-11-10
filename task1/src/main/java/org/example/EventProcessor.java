package org.example;

public class EventProcessor {
    private final MovedEventProcessor movedEventProcessor;
    private final StoppedEventProcessor stoppedEventProcessor;

    public EventProcessor(MovedEventProcessor movedEventProcessor, StoppedEventProcessor stoppedEventProcessor) {
        this.movedEventProcessor = movedEventProcessor;
        this.stoppedEventProcessor = stoppedEventProcessor;
    }

    public void processEvent(Event event) {
        if (EventType.MOVED.equals(event.type())) {
            movedEventProcessor.process(event.payload());
        } else if (EventType.STOPPED.equals(event.type())) {
            stoppedEventProcessor.process(event.payload());
        }
    }
}
