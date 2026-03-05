public class Artist {
    private String name;

    public Artist(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Artist name cannot be blank.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) return;
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return name;
    }
}