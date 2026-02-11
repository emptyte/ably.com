CREATE TABLE users
(
  id         VARCHAR(255) NOT NULL,
  username   VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  phone      VARCHAR(255) NOT NULL,
  tenant_id  VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT uk_users_username UNIQUE (username),
  CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE INDEX idx_users_tenant ON users (tenant_id);
