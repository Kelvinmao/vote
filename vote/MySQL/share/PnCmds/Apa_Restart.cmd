@echo off

rem -- http://www.upupw.net
rem -- webmaster@upupw.net

setlocal enableextensions
if exist MySQL\share\Pn\Config.cmd pushd . & goto cfg
if exist ..\MySQL\share\Pn\Config.cmd pushd .. & goto cfg
goto :eof

:cfg
call MySQL\share\Pn\Config.cmd
if "%php%"=="" exit /b
prompt -$g

pushd %htd_dir%
bin\%htd_exe% -k restart -n %htd_svc% || (pushd .. & %pause% & popd)
echo.
echo    Apache restarts successfully!
popd

prompt
popd
