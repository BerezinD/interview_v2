package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EventProcessorTest {
    @Mock
    MovedEventProcessor movedEventProcessor;

    @Mock
    StoppedEventProcessor stoppedEventProcessor;

    EventProcessor eventProcessor;

    @BeforeEach
    void setUp() {
        eventProcessor = new EventProcessor(movedEventProcessor, stoppedEventProcessor);
    }

    @Test
    void processMovedEvent() {
        eventProcessor.processEvent(new Event(EventType.MOVED, "payloadMoved"));
        Mockito.verify(movedEventProcessor).process("payloadMoved");
        Mockito.verify(stoppedEventProcessor, Mockito.never()).process(Mockito.anyString());
    }

    @Test
    void processStoppedEvent() {
        eventProcessor.processEvent(new Event(EventType.STOPPED, "payloadStopped"));
        Mockito.verify(stoppedEventProcessor).process("payloadStopped");
        Mockito.verify(movedEventProcessor, Mockito.never()).process(Mockito.anyString());
    }
}
