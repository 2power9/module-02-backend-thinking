package elevatorSystem;

enum Direction {
    UP(0), 
    DOWN(1),
    STAY(2);
    private final int value;

    Direction(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}