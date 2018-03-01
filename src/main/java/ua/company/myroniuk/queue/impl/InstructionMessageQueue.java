package ua.company.myroniuk.queue.impl;

import ua.company.myroniuk.message.InstructionMessage;
import ua.company.myroniuk.queue.Queue;
import java.util.PriorityQueue;

public class InstructionMessageQueue implements Queue<InstructionMessage> {

    private PriorityQueue<InstructionMessage> priorityQueue;

    public InstructionMessageQueue() {
        priorityQueue = new PriorityQueue<>((m1, m2) -> {
            Integer value1 = getValue(m1);
            Integer value2 = getValue(m2);
            return value1.equals(value2) ? 1 : (value1 - value2);
        });
    }

    @Override
    public void enqueue(InstructionMessage message) {
        priorityQueue.offer(message);
    }

    @Override
    public InstructionMessage dequeue() {
        return priorityQueue.poll();
    }

    @Override
    public InstructionMessage peek() {
        return priorityQueue.peek();
    }

    @Override
    public int count() {
        return priorityQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    PriorityQueue<InstructionMessage> getPriorityQueue() {
        return priorityQueue;
    }

    private Integer getValue(InstructionMessage message) {
        return message.getInstructionType().getPriority().getValue();
    }

}
