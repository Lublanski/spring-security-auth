CREATE TABLE IF NOT EXISTS user_role(
    id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES "user"(id),
    FOREIGN KEY(role_id) REFERENCES "role"(id)
)