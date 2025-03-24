CREATE TABLE sensor (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    description TEXT,

    range_from INT,
    range_to INT NOT NULL,

    type_id BIGINT NOT NULL REFERENCES sensor_type,
    unit_id BIGINT NOT NULL REFERENCES sensor_unit
);

COMMENT ON TABLE sensor IS 'Основная таблица сенсоров. Хранит данные о сенсоре, его типе и единице измерения.';

COMMENT ON COLUMN sensor.id IS 'Уникальный идентификатор сенсора.';
COMMENT ON COLUMN sensor.name IS 'Название сенсора.';
COMMENT ON COLUMN sensor.model IS 'Модель сенсора.';
COMMENT ON COLUMN sensor.location IS 'Расположение сенсора.';
COMMENT ON COLUMN sensor.description IS 'Дополнительное описание сенсора.';

COMMENT ON COLUMN sensor.range_from IS 'Минимальное значение, при котором сенсор может работать. Должно быть положительным.';
COMMENT ON COLUMN sensor.range_to IS 'Максимальное значение, при котором сенсор может работать. Должно быть положительным.';

COMMENT ON COLUMN sensor.type_id IS 'Ссылка на тип сенсора.';
COMMENT ON COLUMN sensor.unit_id IS 'Ссылка на единицу измерения.';
