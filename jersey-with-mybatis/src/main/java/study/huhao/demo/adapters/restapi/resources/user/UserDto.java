package study.huhao.demo.adapters.restapi.resources.user;

import study.huhao.demo.adapters.restapi.resources.ResponseDto;
import study.huhao.demo.domain.contexts.usercontext.user.User;

import java.time.Instant;

/**
 * UserDto:
 * @author zhangxuhai
 * @date 2020/1/29
*/
public class UserDto implements ResponseDto {
    private String id;
    private String name;
    private String displayName;
    private String signature;
    private String email;
    private Instant createdAt;
    private Instant savedAt;

    public UserDto(String id, String name, String displayName, String signature, String email, Instant createdAt, Instant savedAt) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.signature = signature;
        this.email = email;
        this.createdAt = createdAt;
        this.savedAt = savedAt;
    }

    public String getId() {
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

    static UserDto of(User user) {
        return new UserDto(
                user.getId().toString(),
                user.getName(),
                user.getDisplayName(),
                user.getSignature(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getSavedAt()
        );
    }
}
