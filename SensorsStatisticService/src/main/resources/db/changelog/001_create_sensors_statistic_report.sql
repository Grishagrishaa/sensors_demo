CREATE TABLE sensors_statistic_report (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  total BIGINT NOT NULL
);

CREATE TABLE sensor_type_count (
    statistics_id UUID NOT NULL,
    sensor_type VARCHAR(255) NOT NULL,
    count INTEGER NOT NULL,
    FOREIGN KEY (statistics_id) REFERENCES sensors_statistic_report(id) ON DELETE CASCADE
);