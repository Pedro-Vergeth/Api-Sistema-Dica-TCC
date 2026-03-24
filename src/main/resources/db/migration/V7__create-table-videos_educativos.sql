CREATE TABLE video_educativo (
                                    id SERIAL PRIMARY KEY,
                                    titulo VARCHAR(150) NOT NULL,
                                    duracao_segundos INT,
                                    video_url VARCHAR(255) NOT NULL
);