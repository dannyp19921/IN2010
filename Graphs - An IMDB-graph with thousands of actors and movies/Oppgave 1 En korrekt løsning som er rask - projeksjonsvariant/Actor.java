// Klasse som representerer en skuespiller
class Actor {
    private String id;
    private String name;

    public Actor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}