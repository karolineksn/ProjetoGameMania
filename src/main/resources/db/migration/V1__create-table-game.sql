DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'games') THEN
        CREATE TABLE games (
            id SERIAL PRIMARY KEY,
            title TEXT NOT NULL,
            price INTEGER NOT NULL,
            image TEXT,
            available BOOLEAN DEFAULT true
        );
    END IF;
END $$;
