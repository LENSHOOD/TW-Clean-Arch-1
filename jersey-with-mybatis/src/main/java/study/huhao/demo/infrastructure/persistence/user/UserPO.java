package study.huhao.demo.infrastructure.persistence.user;

import study.huhao.demo.domain.contexts.usercontext.user.User;

import java.time.Instant;
import java.util.UUID;

/**
 * UserPO:
 * @author zhangxuhai
 * @date 2020/1/28
*/
public class UserPO {
    private String id;
    private String userName;
    private String displayName;
    private String signature;
    private String email;
    private Instant createdAt;
    private Instant savedAt;

    UserPO() {
    }

    UserPO(String id, String userName, String displayName, String signature, String email, Instant createdAt, Instant savedAt) {
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
        this.signature = signature;
        this.email = email;
        this.createdAt = createdAt;
        this.savedAt = savedAt;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
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

    static UserPO of(User user) {
        return new UserPO(
                user.getId().toString(),
                user.getName(),
                user.getDisplayName(),
                user.getSignature(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getSavedAt()
        );
    }

    public static User toDomainModel(UserPO userPO) {
        return new User(UUID.fromString(userPO.getId()),
                userPO.getUserName(),
                userPO.getDisplayName(),
                userPO.getSignature(),
                userPO.getEmail(),
                userPO.getCreatedAt(),
                userPO.getSavedAt());
    }
}
