CREATE TABLE writers
(
    id        SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName  VARCHAR(50) NOT NULL
);

CREATE TABLE "post"
(
    id         SERIAL PRIMARY KEY,
    content    TEXT,
    created    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status     VARCHAR(20) NOT NULL CHECK (status IN ('ACTIVE', 'UNDER_REVIEW', 'DELETED')),
    writer_id  INT,
    CONSTRAINT fk_post_writer FOREIGN KEY (writer_id) REFERENCES writers (id) ON DELETE SET NULL
);

CREATE TABLE "label"
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE post_Label
(
    post_id  INT NOT NULL,
    label_id INT NOT NULL,
    CONSTRAINT fk_post_label_post FOREIGN KEY (post_id) REFERENCES "post" (id) ON DELETE CASCADE,
    CONSTRAINT fk_post_label_label FOREIGN KEY (label_id) REFERENCES "label" (id) ON DELETE CASCADE
);
