package dev.wowhelper.macrogenerator.model;

public enum MacroType {
    FOCUS("Focus"),
    PLAYER("Player"),
    CURSOR("Cursor");

    private final String displayName;

    private MacroType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
