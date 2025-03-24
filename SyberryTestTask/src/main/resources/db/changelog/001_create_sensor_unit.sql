CREATE TABLE sensor_unit (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

COMMENT ON TABLE sensor_unit IS 'Справочник единиц измерения сенсоров (например, "градусы", "метры").';

COMMENT ON COLUMN sensor_unit.id IS 'Уникальный идентификатор единицы измерения.';
COMMENT ON COLUMN sensor_unit.name IS 'Название единицы измерения (например, "градусы", "метры"). Должно быть уникальным.';
