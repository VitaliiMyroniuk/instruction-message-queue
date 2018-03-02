package ua.company.myroniuk.receiver.impl;

import ua.company.myroniuk.message.InstructionMessage;
import ua.company.myroniuk.parser.Parser;
import ua.company.myroniuk.queue.Queue;
import ua.company.myroniuk.receiver.MessageReceiver;
import ua.company.myroniuk.validator.Validator;

public class InstructionMessageReceiver implements MessageReceiver {

    private Parser<InstructionMessage> parser;

    private Validator<InstructionMessage> validator;

    private Queue<InstructionMessage> queue;

    public InstructionMessageReceiver(Parser<InstructionMessage> parser,
                                      Validator<InstructionMessage> validator, Queue<InstructionMessage> queue) {
        this.parser = parser;
        this.validator = validator;
        this.queue = queue;
    }

    @Override
    public void receive(String message) {
        InstructionMessage instructionMessage = parser.parse(message);
        validator.validate(instructionMessage);
        queue.enqueue(instructionMessage);
    }

    public Parser<InstructionMessage> getParser() {
        return parser;
    }

    public void setParser(Parser<InstructionMessage> parser) {
        this.parser = parser;
    }

    public Validator<InstructionMessage> getValidator() {
        return validator;
    }

    public void setValidator(Validator<InstructionMessage> validator) {
        this.validator = validator;
    }

    public Queue<InstructionMessage> getQueue() {
        return queue;
    }

    public void setQueue(Queue<InstructionMessage> queue) {
        this.queue = queue;
    }

}
