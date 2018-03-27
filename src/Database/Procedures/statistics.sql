DROP PROCEDURE IF EXISTS statistics; -- will drop the current version if exists!

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `statistics`(
    IN `user_id_input` INT,
    IN `zones_input` VARCHAR(64),
    IN `velocity_initial` DOUBLE,
    IN `velocity_final` DOUBLE,
    IN `angle_initial` DOUBLE,
    IN `angle_final` DOUBLE,
    IN `date_initial` DATETIME,
    IN `date_final` DATETIME
)
BEGIN
    set @base = 'SELECT zone.zone_id, omega.velocity, omega.angle, return_rate.returned, return_rate.time_stamp
    FROM shot_type
    JOIN omega ON shot_type.omega_id = omega.omega_id
    JOIN zone ON shot_type.zone_id = zone.zone_id
    JOIN return_rate ON shot_type.shot_id = return_rate.shot_id
    WHERE 1 = 1';

    IF !(user_id_input IS NULL) THEN
        set @base = concat(@base, ' AND (return_rate.user_id = ',user_id_input,')');
    END IF;

    IF !(zones_input IS NULL) THEN
        set @base = concat(@base, ' AND (shot_type.zone_id IN (' , zones_input , '-1))');
    END IF;

    IF !(velocity_initial IS NULL OR velocity_final IS NULL) THEN
        set @base = concat(@base, ' AND (omega.velocity BETWEEN ',velocity_initial,' AND ',velocity_final,')');
    END IF;

    IF !(angle_initial IS NULL OR angle_final IS NULL) THEN
        set @base = concat(@base, ' AND (omega.angle BETWEEN ',angle_initial,' AND ',angle_final,')');
    END IF;

    IF !(date_initial IS NULL OR date_final IS NULL) THEN
        set @base = concat(@base, ' AND (return_rate.time_stamp BETWEEN "',date_initial,'" AND "',date_final,'")');
    END IF;

    PREPARE stmt FROM @base;
    EXECUTE stmt;

END//
DELIMITER ;
