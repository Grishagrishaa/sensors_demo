CREATE TABLE sensor_type (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

COMMENT ON TABLE sensor_type IS 'Справочник типов сенсоров (например, "термометр", "барометр").';

COMMENT ON COLUMN sensor_type.id IS 'Уникальный идентификатор типа сенсора.';
COMMENT ON COLUMN sensor_type.name IS 'Название типа сенсора. Должно быть уникальным.';
