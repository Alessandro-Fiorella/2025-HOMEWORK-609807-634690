package it.uniroma3.diadia.ambienti;

public enum Direzione {
    NORD, SUD, EST, OVEST;

    public static Direzione fromString(String dir) {
        switch (dir.toLowerCase()) {
            case "nord": return NORD;
            case "sud": return SUD;
            case "est": return EST;
            case "ovest": return OVEST;
            default: throw new IllegalArgumentException("Direzione non valida: " + dir);
        }
    }

    public Direzione opposta() {
        switch (this) {
            case NORD: return SUD;
            case SUD:  return NORD;
            case EST:  return OVEST;
            case OVEST:return EST;
            default: throw new IllegalStateException("Direzione non valida: " + this);
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
