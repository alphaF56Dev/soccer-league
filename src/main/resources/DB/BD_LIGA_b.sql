CREATE TABLE country (
  id_country BIGSERIAL NOT NULL,
  country_name VARCHAR(100) NOT NULL,
  iso_code VARCHAR(5) NOT NULL,
  PRIMARY KEY(id_country)
);

CREATE TABLE member_type (
  id_member_type BIGSERIAL NOT NULL,
  member_type_name VARCHAR(35) NOT NULL,
  isActive BOOL NOT NULL DEFAULT TRUE,
  code VARCHAR(10) NULL,
  PRIMARY KEY(id_member_type)
);

CREATE TABLE category_league (
  id_category_league BIGSERIAL NOT NULL,
  category_name VARCHAR(35) NOT NULL,
  isActive BOOL NOT NULL DEFAULT TRUE,
  minAge INTEGER NOT NULL,
  maxAge INTEGER NOT NULL,
  description VARCHAR(200) NULL,
  duration SMALLINT NOT NULL DEFAULT 90,
  PRIMARY KEY(id_category_league)
);

CREATE TABLE member (
  id_member BIGSERIAL NOT NULL,
  id_member_type BIGINT NOT NULL,
  name_member VARCHAR(200) NOT NULL,
  personal_id VARCHAR(35) NOT NULL,
  birthday DATE NOT NULL,
  phone_number VARCHAR(25) NOT NULL,
  sex CHAR(3) NOT NULL,
  email VARCHAR(150) NOT NULL,
  isActive BOOL NOT NULL DEFAULT TRUE,
  nationality VARCHAR(35) NULL,
  PRIMARY KEY(id_member),
  FOREIGN KEY(id_member_type)
    REFERENCES member_type(id_member_type)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE state (
  id_state BIGSERIAL NOT NULL,
  id_country BIGINT NOT NULL,
  state_name VARCHAR(150) NOT NULL,
  code VARCHAR(10) NULL,
  short_name VARCHAR(10) NULL,
  PRIMARY KEY(id_state),
  FOREIGN KEY(id_country)
    REFERENCES country(id_country)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE team (
  id_team BIGSERIAL NOT NULL,
  id_member BIGINT NOT NULL,
  team_name VARCHAR(100) NOT NULL UNIQUE,
  registration_date DATE NOT NULL,
  isActive BOOL NULL DEFAULT TRUE,
  PRIMARY KEY(id_team),
  FOREIGN KEY(id_member)
    REFERENCES member(id_member)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE team_category_league (
  id_team_category_league BIGSERIAL NOT NULL,
  id_team BIGINT NOT NULL,
  id_category_league BIGINT NOT NULL,
  PRIMARY KEY(id_team_category_league),
  FOREIGN KEY(id_category_league)
    REFERENCES category_league(id_category_league)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_team)
    REFERENCES team(id_team)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE referee (
  id_referee BIGSERIAL NOT NULL,
  id_member BIGINT NOT NULL,
  isActive BOOL NULL DEFAULT TRUE,
  PRIMARY KEY(id_referee),
  FOREIGN KEY(id_member)
    REFERENCES member(id_member)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE position_catalog (
  idposition_catalog SERIAL NOT NULL,
  position_name VARCHAR(70) NULL UNIQUE,
  code VARCHAR(5) NULL,
  description VARCHAR(250) NULL,
  PRIMARY KEY(idposition_catalog)
);

CREATE TABLE player (
  id_player BIGSERIAL NOT NULL,
  id_member BIGINT NOT NULL,
  nickname VARCHAR(35) NULL,
  idposition_catalog INTEGER,
  PRIMARY KEY(id_player),
  FOREIGN KEY(id_member)
    REFERENCES member(id_member)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY (idposition_catalog)
    REFERENCES position_catalog(idposition_catalog)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE municipality (
  id_municipality BIGSERIAL NOT NULL,
  id_state BIGINT NOT NULL,
  municipality_name VARCHAR(150) NOT NULL,
  short_name VARCHAR(20) NULL,
  PRIMARY KEY(id_municipality),
  FOREIGN KEY(id_state)
    REFERENCES state(id_state)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE soccer_field (
  id_soccer_field BIGSERIAL NOT NULL,
  id_category_league BIGINT NOT NULL,
  id_municipality BIGINT NOT NULL,
  field_name VARCHAR(35) NULL,
  full_direction VARCHAR(400) NULL,
  PRIMARY KEY(id_soccer_field),
  FOREIGN KEY(id_category_league)
    REFERENCES category_league(id_category_league)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_municipality)
    REFERENCES municipality(id_municipality)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE player_category (
  id_player_category BIGSERIAL NOT NULL,
  id_team_category_league BIGINT NOT NULL,
  id_player BIGINT NOT NULL,
  registration_date DATE NULL,
  player_number SMALLINT NOT NULL,
  PRIMARY KEY(id_player_category),
  FOREIGN KEY(id_player)
    REFERENCES player(id_player)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_team_category_league)
    REFERENCES team_category_league(id_team_category_league)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE direction (
  id_direction BIGSERIAL NOT NULL,
  id_municipality BIGINT NOT NULL,
  id_member BIGINT NOT NULL,
  direction_name VARCHAR(100) NOT NULL,
  street VARCHAR(150) NOT NULL,
  ext_number VARCHAR(15) NOT NULL,
  int_number VARCHAR(15) NULL,
  suburb VARCHAR(150) NOT NULL,
  zip VARCHAR(25) NOT NULL,
  full_direction TEXT NOT NULL,
  PRIMARY KEY(id_direction),
  FOREIGN KEY(id_member)
    REFERENCES member(id_member)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_municipality)
    REFERENCES municipality(id_municipality)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE category_league_has_referee (
  id_category_referee BIGSERIAL NOT NULL,
  id_referee BIGINT NOT NULL,
  id_category_league BIGINT NOT NULL,
  PRIMARY KEY(id_category_referee),
  FOREIGN KEY(id_category_league)
    REFERENCES category_league(id_category_league)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_referee)
    REFERENCES referee(id_referee)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE game (
  id_game BIGSERIAL NOT NULL,
  id_referee BIGINT NOT NULL,
  id_category_league BIGINT NOT NULL,
  id_soccer_field BIGINT NOT NULL,
  id_team_local BIGINT NOT NULL,
  id_team_visitor BIGINT NOT NULL,
  game_date TIMESTAMP NOT NULL,
  local_goals SMALLINT NULL DEFAULT 0,
  vistor_goals SMALLINT NULL DEFAULT 0,
  first_extra_time SMALLINT NULL,
  second_extra_time SMALLINT NULL,
  first_half_start TIMESTAMP NULL,
  second_half_start TIMESTAMP NULL,
  first_half_finished BOOL NULL DEFAULT FALSE,
  second_half_finished BOOL NULL DEFAULT FALSE,
  PRIMARY KEY(id_game),
  FOREIGN KEY(id_team_local)
    REFERENCES team(id_team)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_category_league)
    REFERENCES category_league(id_category_league)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_team_visitor)
    REFERENCES team(id_team)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_soccer_field)
    REFERENCES soccer_field(id_soccer_field)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_referee)
    REFERENCES referee(id_referee)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE dead_time (
  id_dead_time BIGSERIAL NOT NULL,
  id_game BIGINT NOT NULL,
  start_dead_time SMALLINT NOT NULL,
  end_dead_time SMALLINT NOT NULL,
  duration SMALLINT NULL,
  part_time SMALLINT NOT NULL,
  PRIMARY KEY(id_dead_time),
  FOREIGN KEY(id_game)
    REFERENCES game(id_game)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE game_assistance (
  id_game_assistance BIGSERIAL NOT NULL,
  id_player BIGINT NOT NULL,
  id_game BIGINT NOT NULL,
  id_team BIGINT NOT NULL,
  is_main_player BOOL NOT NULL DEFAULT TRUE,
  PRIMARY KEY(id_game_assistance),
  FOREIGN KEY(id_game)
    REFERENCES game(id_game)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_team)
    REFERENCES team(id_team)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_player)
    REFERENCES player(id_player)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE game_card (
  id_game_card BIGSERIAL NOT NULL,
  id_game BIGINT NOT NULL,
  id_game_assistance BIGINT NOT NULL,
  card_minute SMALLINT NOT NULL,
  type_card VARCHAR(15) NULL,
  in_extra_time BOOL NULL DEFAULT FALSE,
  extra_time SMALLINT NULL DEFAULT 0,
  PRIMARY KEY(id_game_card),
  FOREIGN KEY(id_game_assistance)
    REFERENCES game_assistance(id_game_assistance)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_game)
    REFERENCES game(id_game)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE game_goal (
  id_game_goal BIGSERIAL NOT NULL,
  id_game BIGINT NOT NULL,
  id_game_assistance BIGINT NOT NULL,
  goal_minute SMALLINT NOT NULL,
  isAutoGoal BOOL NOT NULL DEFAULT FALSE,
  in_extra_time BOOL NOT NULL DEFAULT FALSE,
  extra_time SMALLINT NULL DEFAULT 0,
  PRIMARY KEY(id_game_goal),
  FOREIGN KEY(id_game)
    REFERENCES game(id_game)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_game_assistance)
    REFERENCES game_assistance(id_game_assistance)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE game_change (
  id_game_change BIGSERIAL NOT NULL,
  id_game BIGINT NOT NULL,
  id_game_assistance_out BIGINT NOT NULL,
  id_game_assistance_in BIGINT NOT NULL,
  change_minute SMALLINT NOT NULL,
  in_extra_time BOOL NULL DEFAULT FALSE,
  extra_time SMALLINT NULL DEFAULT 0,
  PRIMARY KEY(id_game_change),
  FOREIGN KEY(id_game_assistance_in)
    REFERENCES game_assistance(id_game_assistance)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_game_assistance_out)
    REFERENCES game_assistance(id_game_assistance)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_game)
    REFERENCES game(id_game)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


CREATE TABLE user_access (
  id_user_access BIGSERIAL NOT NULL,
  id_member BIGINT,
  username VARCHAR(35) NOT NULL UNIQUE,
  pwd VARCHAR(200) NOT NULL,
  isActive BOOL DEFAULT TRUE,
  registration_date TIMESTAMP DEFAULT CURRENT_DATE,
  blockedOn TIMESTAMP NULL,
  PRIMARY KEY(id_user_access),
  FOREIGN KEY (id_member)
  	REFERENCES member(id_member)
	  ON DELETE NO ACTION
	  ON UPDATE NO ACTION
);