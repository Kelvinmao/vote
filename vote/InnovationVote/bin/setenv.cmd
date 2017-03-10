@echo on
@rem This script should be used to set up your environment for
@rem compiling and running WebLogic Server for Partner.

set TOM_HOME=C:\vote\Tomcat

SET DATABASE_HOME=C:\vote\MySQL

SET VOTE_HOME=C:\vote\InnovationVote\WEB-INF


@rem Set JAVA_VENDOR to java virtual machine you want to run on server side.
set JAVA_VENDOR=Sun

@rem Set JAVA_HOME to java virtual machine you want to run on server side.
set JAVA_HOME=C:\vote\jdk1.8

@rem set JAVA_VM=
SET PATH=%JAVA_HOME%\bin;%VOTE_HOME%\bin;%PATH%

SET DRV_HOME=C:\vote\Tomcat\common\lib
set CLASSPATH=%JAVA_HOME%\bin;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\jre\lib\rt.jar;%TOM_HOME%\lib\servlet-api.jar;%DRV_HOME%\jsp-api.jar;%VOTE_HOME%\classes;%DRV_HOME%\msbase.jar;%DRV_HOME%\mssqlserver.jar;%DRV_HOME%\msutil.jar;%VOTE_HOME%\lib\antlr-2.7.7.jar;%VOTE_HOME%\lib\asm-5.0.2.jar;%VOTE_HOME%\lib\asm-commons-5.0.2.jar;%VOTE_HOME%\lib\asm-tree-5.0.2.jar;%VOTE_HOME%\lib\commons-fileupload-1.3.1.jar;%VOTE_HOME%\lib\commons-io-2.2.jar;%VOTE_HOME%\lib\commons-lang3-3.2.jar;%VOTE_HOME%\lib\freemarker-2.3.19.jar;%VOTE_HOME%\lib\jakarta-oro.jar;%VOTE_HOME%\lib\javassist-3.11.0.GA.jar;%VOTE_HOME%\lib\ognl-3.0.6.jar;%VOTE_HOME%\lib\struts2-core-2.3.20.jar;%VOTE_HOME%\lib\xwork-core-2.3.20.jar;%VOTE_HOME%\lib\dom4j-1.6.1.jar;%VOTE_HOME%\lib\hibernate-commons-annotations-4.0.5.Final.jar;%VOTE_HOME%\lib\hibernate-core-4.3.8.Final.jar;%VOTE_HOME%\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;%VOTE_HOME%\lib\jandex-1.1.0.Final.jar;%VOTE_HOME%\lib\javassist-3.18.1-GA.jar;%VOTE_HOME%\lib\jboss-logging-3.1.3.GA.jar;%VOTE_HOME%\lib\jboss-logging-annotations-1.2.0.Beta1.jar;%VOTE_HOME%\lib\jboss-transaction-api_1.2_spec-1.0.0.Final.jar;%VOTE_HOME%\lib\mysqldriver.jar;%VOTE_HOME%\lib\log4j-1.2.17.jar;%VOTE_HOME%\log4j.properties;