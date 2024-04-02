-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER posipals_owner
WITH PASSWORD 'posipals';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO posipals_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO posipals_owner;

CREATE USER posipals_appuser
WITH PASSWORD 'posipals';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO posipals_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO posipals_appuser;
