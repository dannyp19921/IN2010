class Actor {
    private String actorId; 
    private String actorName; 

    public Actor(String actorId, String actorName) {
        this.actorId = actorId; 
        this.actorName = actorName;
    }

    public String getId() {
        return actorId; 
    }

    public String getName() {
        return actorName; 
    }

    @Override
    public String toString() {
        return actorName; 
    }
}
