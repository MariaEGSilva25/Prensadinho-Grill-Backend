package com.unasp.Prensadinho.ENUM;

public enum PaymentType {
    DEBIT(1),
    CREDIT(2),
    PIX(3),
    MONEY(4);

    private final int id;

    PaymentType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PaymentType fromId(int id) {
        for (PaymentType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("ID not found: " + id);
    }
}
