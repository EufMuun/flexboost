-- Create a new database user
CREATE USER flexdev WITH PASSWORD 'flexdev';

-- Create a new schema
CREATE SCHEMA flex_schema ;

-- Set search path to the new schema
SET search_path TO flex_schema;

-- Create tables in the new schema
CREATE TABLE user_credentials (
    uID SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_info (
    uID INTEGER PRIMARY KEY,
    displayName VARCHAR(255),
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    dateOfBirth DATE,
    FOREIGN KEY (uID) REFERENCES user_credentials(uID)
);

CREATE TABLE profile (
    profileID SERIAL PRIMARY KEY,
    profileURL VARCHAR(255),
    banner_pic_link VARCHAR(255),
    pfpLink VARCHAR(255),
    bio TEXT,
    description TEXT
);

CREATE TABLE user_profile (
    uID INTEGER,
    profileID INTEGER,
    PRIMARY KEY (uID, profileID),
    FOREIGN KEY (uID) REFERENCES user_credentials(uID),
    FOREIGN KEY (profileID) REFERENCES profile(profileID)
);

CREATE TABLE post (
    postID SERIAL PRIMARY KEY,
    uID INTEGER,
    FOREIGN KEY (uID) REFERENCES user_credentials(uID)
);

CREATE TABLE post_contents (
    postID INTEGER PRIMARY KEY,
    postBannerLink VARCHAR(255),
    postMedia TEXT,
    description TEXT,
    FOREIGN KEY (postID) REFERENCES post(postID)
);

CREATE TABLE post_media (
    postID INTEGER,
    mediaLink VARCHAR(255),
    PRIMARY KEY (postID, mediaLink),
    FOREIGN KEY (postID) REFERENCES post(postID)
);

CREATE TABLE tags (
    tag VARCHAR(255) PRIMARY KEY
);

CREATE TABLE post_tags (
    postID INTEGER,
    tag VARCHAR(255),
    PRIMARY KEY (postID, tag),
    FOREIGN KEY (postID) REFERENCES post(postID),
    FOREIGN KEY (tag) REFERENCES tags(tag)
);

CREATE EXTENSION pgcrypto;

GRANT USAGE ON SCHEMA flex_schema TO flexdev;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA flex_schema TO flexdev;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA flex_schema TO flexdev ;
ALTER DEFAULT PRIVILEGES IN SCHEMA flex_schema GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO flexdev;
