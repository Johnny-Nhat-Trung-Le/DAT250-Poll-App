package entities;

import java.io.Serializable;
import java.time.Instant;

public class Vote implements Serializable {

    private Instant publishedAt;

    public Vote() {
    }

    public Vote(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }
}
