CREATE OR REPLACE trigger h19_07_ora."APEX$_WS_FILES_T1"
before insert or update on h19_07_ora."APEX$_WS_FILES"
for each row
begin
    --
    -- maintain pk and timestamps
    --
    if inserting and :new.id is null then
        select to_number(sys_guid(),'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX') into :new.id from dual;
    end if;
    if inserting and :new.image_alias is null then
        :new.image_alias := :new.name;
    end if;
    if inserting then
        :new.created_on := sysdate;
        :new.created_by := nvl(v('APP_USER'),user);
        :new.updated_on := sysdate;
        :new.updated_by := nvl(v('APP_USER'),user);
        :new.content_last_update := sysdate;
    elsif updating then
        :new.updated_on := sysdate;
        :new.updated_by := nvl(v('APP_USER'),user);
        if nvl(length(:new.content),0) <> nvl(length(:old.content),0) then
            :new.content_last_update := sysdate;
        end if;
    end if;
end;
/