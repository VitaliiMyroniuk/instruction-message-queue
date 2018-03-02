package ua.company.myroniuk.receiver.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.company.myroniuk.exception.InstructionMessageParseException;
import ua.company.myroniuk.exception.InstructionMessageValidationException;
import ua.company.myroniuk.message.InstructionMessage;
import ua.company.myroniuk.parser.Parser;
import ua.company.myroniuk.queue.Queue;
import ua.company.myroniuk.validator.Validator;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InstructionMessageReceiverTest {

    private static final String MESSAGE = "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z\n";

    @Mock
    private Parser<InstructionMessage> parser;

    @Mock
    private Validator<InstructionMessage> validator;

    @Mock
    private Queue<InstructionMessage> queue;

    @InjectMocks
    private InstructionMessageReceiver receiver;

    private InstructionMessage instructionMessage;

    @Before
    public void init() {
        instructionMessage = new InstructionMessage();
    }

    @Test
    public void shouldReceiveValidInstructionMessage() {
        doReturn(instructionMessage).when(parser).parse(MESSAGE);
        InOrder inOrder = inOrder(parser, validator, queue);

        receiver.receive(MESSAGE);

        inOrder.verify(parser).parse(MESSAGE);
        inOrder.verify(validator).validate(instructionMessage);
        inOrder.verify(queue).enqueue(instructionMessage);
    }

    @Test
    public void shouldNotReceiveUnparsedInstructionMessage() {
        doThrow(InstructionMessageParseException.class).when(parser).parse(MESSAGE);

        try {
            receiver.receive(MESSAGE);
        } catch (Exception e) {
            assertEquals(InstructionMessageParseException.class, e.getClass());
        }

        verify(parser).parse(MESSAGE);
        verify(validator, never()).validate(instructionMessage);
        verify(queue, never()).enqueue(instructionMessage);
    }

    @Test
    public void shouldNotReceiveInvalidInstructionMessage() {
        doReturn(instructionMessage).when(parser).parse(MESSAGE);
        doThrow(InstructionMessageValidationException.class).when(validator).validate(instructionMessage);

        try {
            receiver.receive(MESSAGE);
        } catch (Exception e) {
            assertEquals(InstructionMessageValidationException.class, e.getClass());
        }

        verify(parser).parse(MESSAGE);
        verify(validator).validate(instructionMessage);
        verify(queue, never()).enqueue(instructionMessage);
    }

}