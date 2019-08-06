CREATE OR REPLACE TRIGGER  h19_07_ora."DEMO_USERS_T1"
BEFORE
insert or update on h19_07_ora."DEMO_USERS"
for each row
begin
:NEW.user_name := upper(:NEW.user_name);
end;
/