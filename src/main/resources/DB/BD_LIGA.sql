CREATE TABLE category_league (
  id_category_league SERIAL NOT NULL ,
  category_name VARCHAR(35) NOT NULL,
  isActive BOOL NOT NULL DEFAULT TRUE,
  minAge INTEGER NOT NULL,
  maxAge INTEGER NOT NULL,
  description VARCHAR(200) NULL,
  duration SMALLINT NOT NULL DEFAULT 90,
  PRIMARY KEY(id_category_league)
);

CREATE TABLE category_league_has_referee (
  id_referee SERIAL NOT NULL,
  id_category_league INTEGER NOT NULL,
  PRIMARY KEY(id_referee, id_category_league) --,
  --INDEX category_league_has_referee_FKIndex1(id_category_league),
  --INDEX category_league_has_referee_FKIndex2(id_referee)
);

CREATE TABLE country (
  id_country SERIAL NOT NULL,
  contry_name VARCHAR(100) NOT NULL,
  iso_code VARCHAR(5) NOT NULL,
  PRIMARY KEY(id_country)
);

CREATE TABLE dead_time (
  id_dead_time SERIAL NOT NULL,
  id_game BIGINT NOT NULL,
  start_dead_time SMALLINT NOT NULL,
  end_dead_time SMALLINT NOT NULL,
  duration SMALLINT NULL,
  part_time SMALLINT NOT NULL,
  PRIMARY KEY(id_dead_time),
  --INDEX dead_time_FKIndex1(id_game)
);

CREATE TABLE direction (
  id_direction SERIAL NOT NULL,
  id_municipality BIGINT NOT NULL,
  id_member BIGINT NOT NULL,
  direction_name VARCHAR(100) NOT NULL,
  street VARCHAR(150) NOT NULL,
  ext_number VARCHAR(15) NOT NULL,
  int_number VARCHAR(15) NULL,
  suburb VARCHAR(150) NOT NULL,
  zip VARCHAR(25) NOT NULL,
  full_direction TEXT NOT NULL,
  PRIMARY KEY(id_direction) --,
  --INDEX direction_FKIndex1(id_member),
  --INDEX direction_FKIndex2(id_municipality)
);

CREATE TABLE game (
  id_game SERIAL NOT NULL,
  id_referee INTEGER NOT NULL,
  id_category_league INTEGER NOT NULL,
  id_soccer_field INTEGER NOT NULL,
  id_team_local BIGINT NOT NULL,
  id_team_visitor BIGINT NOT NULL,
  game_date DATETIME NOT NULL,
  local_goals SMALLINT NULL DEFAULT 0,
  vistor_goals SMALLINT NULL DEFAULT 0,
  first_extra_time SMALLINT NULL,
  second_extra_time SMALLINT NULL,
  first_half_start DATETIME NULL,
  second_half_start DATETIME NULL,
  first_half_finished BOOL NULL DEFAULT 0,
  second_half_finished BOOL NULL DEFAULT 0,
  PRIMARY KEY(id_game) --,
  --INDEX game_FKIndex1(id_team_local),
  --INDEX game_FKIndex2(id_category_league),
  --INDEX game_FKIndex3(id_team_visitor),
  --INDEX game_FKIndex4(id_soccer_field),
  --INDEX game_FKIndex5(id_referee)
);

CREATE TABLE game_assistance (
  id_game_assistance SERIAL NOT NULL,
  id_player BIGINT NOT NULL,
  id_game BIGINT NOT NULL,
  id_team BIGINT NOT NULL,
  is_main_player BOOL NOT NULL DEFAULT 0,
  PRIMARY KEY(id_game_assistance)--,
  --INDEX game_assistance_FKIndex1(id_game),
  --INDEX game_assistance_FKIndex2(id_team),
  --INDEX game_assistance_FKIndex3(id_player)
);

CREATE TABLE game_card (
  id_game_card SERIAL NOT NULL,
  id_game BIGINT NOT NULL,
  id_game_assistance BIGINT NOT NULL,
  card_minute SMALLINT NOT NULL,
  type_card VARCHAR(15) NULL,
  in_extra_time BOOL NULL DEFAULT 0,
  extra_time SMALLINT NULL DEFAULT 0,
  PRIMARY KEY(id_game_card)--,
  --INDEX game_card_FKIndex1(id_game_assistance),
  --INDEX game_card_FKIndex2(id_game)
);

CREATE TABLE game_change (
  id_game_change SERIAL NOT NULL,
  id_game BIGINT NOT NULL,
  id_game_assistance_out BIGINT NOT NULL,
  id_game_assistance_in BIGINT NOT NULL,
  change_minute SMALLINT NOT NULL,
  in_extra_time BOOL NULL DEFAULT 0,
  extra_time SMALLINT NULL DEFAULT 0,
  PRIMARY KEY(id_game_change)--,
  --INDEX game_change_FKIndex1(id_game_assistance_in),
  --INDEX game_change_FKIndex2(id_game_assistance_out),
  --INDEX game_change_FKIndex3(id_game)
);

CREATE TABLE game_goal (
  id_game_goal SERIAL NOT NULL,
  id_game BIGINT NOT NULL,
  id_game_assistance BIGINT NOT NULL,
  goal_minute SMALLINT NOT NULL,
  isAutoGoal BOOL NOT NULL DEFAULT 0,
  in_extra_time BOOL NOT NULL DEFAULT 0,
  extra_time SMALLINT NULL DEFAULT 0,
  PRIMARY KEY(id_game_goal) --,
  --INDEX game_goal_FKIndex1(id_game),
  --INDEX game_goal_FKIndex2(id_game_assistance)
);

CREATE TABLE member (
  id_member SERIAL NOT NULL,
  id_member_type INTEGER NOT NULL,
  name_member VARCHAR(200) NOT NULL,
  personal_id VARCHAR(35) NOT NULL,
  birthday DATE NOT NULL,
  phone_number VARCHAR(25) NOT NULL,
  sex CHAR(3) NOT NULL,
  email VARCHAR(150) NOT NULL,
  isActive BOOL NOT NULL DEFAULT 1,
  nationality VARCHAR(35) NULL,
  PRIMARY KEY(id_member)--,
  --INDEX member_FKIndex1(id_member_type)
);

CREATE TABLE member_type (
  id_member_type SERIAL NOT NULL,
  member_type_name VARCHAR(35) NOT NULL,
  isActive BOOL NOT NULL DEFAULT TRUE,
  code VARCHAR(10) NULL,
  PRIMARY KEY(id_member_type)
);

CREATE TABLE municipality (
  id_municipality SERIAL NOT NULL,
  id_state BIGINT NOT NULL,
  municipality_name VARCHAR(150) NOT NULL,
  short_name VARCHAR(20) NULL,
  PRIMARY KEY(id_municipality)
);

CREATE TABLE player (
  id_player BIGINT NOT NULL,
  member_id_member BIGINT NOT NULL,
  number_player VARCHAR(5) NULL,
  nickname VARCHAR(35) NULL,
  PRIMARY KEY(id_player) --,
  --INDEX player_FKIndex1(member_id_member)
);

CREATE TABLE player_category (
  id_player_category SERIAL NOT NULL,
  id_team_category_league BIGINT NOT NULL,
  id_player BIGINT NOT NULL,
  registration_date DATE NULL,
  PRIMARY KEY(id_player_category) --,
  --INDEX player_category_FKIndex1(id_player),
  --INDEX player_category_FKIndex2(id_team_category_league)
);

CREATE TABLE referee (
  id_referee SERIAL NOT NULL,
  id_member BIGINT NOT NULL,
  isActive BOOL NULL DEFAULT 1,
  PRIMARY KEY(id_referee),
  INDEX referee_FKIndex1(id_member)
);

CREATE TABLE soccer_field (
  id_soccer_field SERIAL NOT NULL,
  id_category_league INTEGER NOT NULL,
  id_municipality BIGINT NOT NULL,
  field_name VARCHAR(35) NULL,
  full_direction VARCHAR(400) NULL,
  PRIMARY KEY(id_soccer_field) --,
  --INDEX soccer_field_FKIndex1(id_category_league),
  --INDEX soccer_field_FKIndex2(id_municipality)
);

CREATE TABLE state (
  id_state SERIAL NOT NULL,
  id_country INTEGER NOT NULL,
  state_name VARCHAR(150) NOT NULL,
  code VARCHAR(10) NULL,
  short_name VARCHAR(10) NULL,
  PRIMARY KEY(id_state),
  INDEX states_FKIndex1(id_country)
);

CREATE TABLE team (
  id_team SERIAL NOT NULL,
  id_member BIGINT NOT NULL,
  team_name VARCHAR(100) NOT NULL,
  registration_date DATE NOT NULL,
  PRIMARY KEY(id_team) --,
  --INDEX team_FKIndex1(id_member)
);

CREATE TABLE team_category_league (
  id_team_category_league SERIAL NOT NULL,
  id_team BIGINT NOT NULL,
  id_category_league INTEGER NOT NULL,
  PRIMARY KEY(id_team_category_league) --,
  --INDEX team_category_league_FKIndex2(id_category_league),
  --INDEX team_category_league_FKIndex2(id_team)
);


