@echo on
@rem This script should be used to set up your environment for
@rem stop tomcat
set TOM_HOME=C:\vote\Tomcat
set DATABASE_HOME=C:\vote\MySQL
set CATALINA_HOME=%TOM_HOME%
call setenv.cmd
call %TOM_HOME%\bin\startup.bat
call %DATABASE_HOME%\bin\mysqld --defaults-file=%DATABASE_HOME%\my.ini --user=root



