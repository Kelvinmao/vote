rem -- http://www.upupw.net
rem -- webmaster@upupw.net

set pn_ver=经典怀旧版
set pn_php=PHP5.2.17

set htd_svc=UPUPW_Apache
set htd_port=80
set myd_svc=UPUPW_MySQL_A
set myd_port=3306


for /d %%d in (*) do (
 if exist %%d\bin\httpd.exe set htd_dir=%%d&& set htd_exe=httpd.exe&& set htd_ver=2.2.24
 if exist %%d\php.exe set php_dir=%%d&& set php_ver=5.2.17
 if exist %%d\bin\mysqld.exe set myd_dir=%%d&& set myd_exe=mysqld.exe&& set myd_ver=5.1.70
 if exist %%d\memcached.exe set mem_dir=%%d&& set mem_ver=1.2.6
)
if "%htd_dir%"=="" echo # Apache Not Found. & pause & exit /b
if "%php_dir%"=="" echo # PHP Not Found. & pause & exit /b
if "%myd_dir%"=="" echo # MySQL Not Found. & pause & exit /b
if "%mem_dir%"=="" echo # memcached Not Found. & pause & exit /b


set php=%php_dir%\php.exe -d extension_dir=.\ext -d date.timezone=UTC -n MySQL\share\Pn\Main.php
set pause=%php% echo ` - 按任意键继续...`; ^&^& pause^>nul

set vhs_cfg=%htd_dir%\conf\extra\httpd-vhosts.conf
set PnCmds=MySQL\share\PnCmds
set cfg_bak_zip=Backup\cfg_bak.zip
set cfg_sckf_zip=Backup\cfg_sckf.zip
set cfg_xnsp_zip=Backup\cfg_xnsp.zip

set Sys32=%SystemRoot%\system32
set Path=%Sys32%;%Sys32%\wbem;%SystemRoot%
set net=%Sys32%\net.exe
if not exist %net% set net=%Sys32%\net1.exe
if not exist %net% echo  # 缺少 %Sys32%\net.exe, 不可继续. &%pause%&set php=&exit /b
%php% "chk_path(getcwd());" || %pause% && set php=
