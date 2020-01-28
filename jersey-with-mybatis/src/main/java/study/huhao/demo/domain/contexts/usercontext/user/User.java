package study.huhao.demo.domain.contexts.usercontext.user;

import study.huhao.demo.domain.core.concepts.AggregateRoot;

import java.time.Instant;
import java.util.UUID;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class User implements AggregateRoot {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\S+@\\S+\\.\\S+$");

    private UUID id;
    private String name;
    private String displayName;
    private String signature;
    private String email;
    private Instant createdAt;
    private Instant savedAt;

    public User(String name, String displayName, String signature, String email) {
        this.id = UUID.randomUUID();
        this.name = isNotBlank(name);
        this.displayName = displayName;
        this.signature = signature;
        this.email = isNullOrEmail(email);
        this.savedAt = this.createdAt = Instant.now();
    }

    private String isNotBlank(String name) {
        if (isNull(name) || name.trim().equals("")) {
            throw new IllegalArgumentException("the user name cannot be null or no content");
        }

        return name;
    }

    private String isNullOrEmail(String email) {
        if (!isNull(email) && !EMAIL_PATTERN.matcher(email).find()) {
            throw new IllegalArgumentException("the email is invalid");
        }

        return email;
    }

    public User(UUID id, String name, String displayName, String signature, String email, Instant createdAt, Instant savedAt) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.signature = signature;
        this.email = email;
        this.createdAt = createdAt;
        this.savedAt = savedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSignature() {
        return signature;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getSavedAt() {
        return savedAt;
    }
}
