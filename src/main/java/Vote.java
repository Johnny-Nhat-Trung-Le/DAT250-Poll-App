import java.io.Serializable;
import java.time.Instant;

public class Vote implements Serializable {

    private Instant publishedAt;

    public Vote() {
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }
}
