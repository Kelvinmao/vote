@echo off

rem -- http://www.upupw.net
rem -- webmaster@upupw.net

setlocal enableextensions
if exist MySQL\share\Pn\Config.cmd pushd . & goto cfg
if exist ..\MySQL\share\Pn\Config.cmd pushd .. & goto cfg
goto :eof


:execmd
echo %1
if exist %1 call %1 && goto :eof
if exist %PnCmds%\%1 call %PnCmds%\%1 && goto :eof
echo # �Ҳ��� %1, ���� %PnCmds% �� %CD% Ŀ¼.
%pause%
goto :eof


:cfg
call MySQL\share\Pn\Config.cmd
if "%php%"=="" exit /b
title ����ֹͣ Apache MySQL PHP������������
echo.
call :execmd Apa_Stop.cmd
echo.
call :execmd My_Stop.cmd
echo.
call :execmd mem_Stop.cmd

popd
