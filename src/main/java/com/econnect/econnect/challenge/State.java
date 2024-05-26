package com.econnect.econnect.challenge;

public enum State {
    DEFAULT(1), TRY(2), FAIL(3), SUCCESS(4);

    private final int value;
    State(int value) {this.value = value;}
    public int getValue() {return value;}
}
