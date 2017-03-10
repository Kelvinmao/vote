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

if exist %myd_dir%\data\%COMPUTERNAME%.pid goto stopsvc
echo   ____________________________________________________________
echo  ^|                                                            ^|
echo  ^|    MySQL 似乎没有运行.                                     ^|
echo  ^|____________________________________________________________^|
echo.

:stopsvc
%net% stop %myd_svc%
set errlevel=%errorlevel%
%myd_dir%\bin\%myd_exe% --remove %myd_svc%
if not %errlevel%==0 %pause%

prompt
popd
