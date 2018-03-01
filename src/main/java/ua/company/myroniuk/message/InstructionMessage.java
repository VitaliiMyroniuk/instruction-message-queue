package ua.company.myroniuk.message;

import java.time.LocalDateTime;
import java.util.Objects;

public class InstructionMessage {

    private InstructionType instructionType;

    private String productCode;

    private Integer quantity;

    private Integer uom;

    private LocalDateTime timestamp;

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUom() {
        return uom;
    }

    public void setUom(Integer uom) {
        this.uom = uom;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructionMessage that = (InstructionMessage) o;
        return instructionType == that.instructionType &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(uom, that.uom) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructionType, productCode, quantity, uom, timestamp);
    }

}
