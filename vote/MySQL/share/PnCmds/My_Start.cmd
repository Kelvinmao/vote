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

if not exist %myd_dir%\data\%COMPUTERNAME%.pid goto startsvc
echo   ____________________________________________________________
echo  ^|                                                            ^|
echo  ^|    MySQL �ƺ��Ѿ�����.                                     ^|
echo  ^|____________________________________________________________^|

set /p input= -^> ����ֹͣ�����? (y/N) 
echo.
if /i "%input%"=="y" goto stopsvc
goto end

:stopsvc
%net% stop %myd_svc%
%myd_dir%\bin\%myd_exe% --remove %myd_svc%

:startsvc
%myd_dir%\bin\%myd_exe% --install %myd_svc% --defaults-file="%CD%\%myd_dir%\my.ini"
%net% start %myd_svc% || %pause%

:end
prompt
popd
