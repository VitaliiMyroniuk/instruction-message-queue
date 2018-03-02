package ua.company.myroniuk.validator.impl;

import ua.company.myroniuk.exception.InstructionMessageValidationException;
import ua.company.myroniuk.message.InstructionMessage;
import ua.company.myroniuk.validator.Validator;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class InstructionMessageValidator implements Validator<InstructionMessage> {

    private static final String PRODUCT_CODE_REGEX = "^[A-Z]{2}\\d{2}$";

    private static final Integer MIN_QUANTITY_VALUE = 1;

    private static final Integer MIN_UOM_VALUE = 0;

    private static final Integer MAX_UOM_VALUE = 255;

    private static final LocalDateTime UNIX_EPOCH = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());

    private static final String NOT_VALID_PRODUCT_CODE =
            "Product code is not valid. Expected value: two uppercase letters followed by two digits.";

    private static final String NOT_VALID_QUANTITY =
            "Quantity is not valid. Expected value: positive integer number.";

    private static final String NOT_VALID_UOM =
            "Uom is not valid. Expected value: integer number between %d and %d inclusively.";

    private static final String NOT_VALID_TIMESTAMP =
            "Timestamp is not valid. Expected value: timestamp being greater than unix epoch " +
                    "and less or equal than current data time.";

    @Override
    public void validate(InstructionMessage message) {
        validateProductCode(message);
        validateQuantity(message);
        validateUom(message);
        validateTimestamp(message);
    }

    private void validateProductCode(InstructionMessage message) {
        if (!message.getProductCode().matches(PRODUCT_CODE_REGEX)) {
            throw new InstructionMessageValidationException(NOT_VALID_PRODUCT_CODE);
        }
    }

    private void validateQuantity(InstructionMessage message) {
        if (message.getQuantity() < MIN_QUANTITY_VALUE) {
            throw new InstructionMessageValidationException(NOT_VALID_QUANTITY);
        }
    }

    private void validateUom(InstructionMessage message) {
        if (message.getUom() < MIN_UOM_VALUE || message.getUom() > MAX_UOM_VALUE) {
            throw new InstructionMessageValidationException(String.format(NOT_VALID_UOM, MIN_UOM_VALUE, MAX_UOM_VALUE));
        }
    }

    private void validateTimestamp(InstructionMessage message) {
        LocalDateTime timestamp = message.getTimestamp();
        LocalDateTime now = LocalDateTime.now();
        if (timestamp.isBefore(UNIX_EPOCH) || timestamp.isEqual(UNIX_EPOCH) || timestamp.isAfter(now)) {
            throw new InstructionMessageValidationException(NOT_VALID_TIMESTAMP);
        }
    }

}
