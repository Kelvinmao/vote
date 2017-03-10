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

:stopsvc
pushd %mem_dir%
memcached -d stop
set errno=%errorlevel%
memcached -d uninstall
set /a errno=%errno%+%errorlevel%
echo memcached stop successfully!
popd

if %errno% GTR 0 %pause%

prompt
popd
