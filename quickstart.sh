#!/bin/sh
_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" > /dev/null && pwd -W)"
_MAVEN_SETTINGS=$_DIR"/.mvn/settings.xml"
_MAVEN_REPO=$_DIR"/.mvn/repository"

cat > $_MAVEN_SETTINGS <<- EOM
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
		          https://maven.apache.org/xsd/settings-1.0.0.xsd">
	<localRepository>$_MAVEN_REPO</localRepository>
</settings>
EOM

pushd $_DIR
./mvnw -s $_MAVEN_SETTINGS clean install
./mvnw -s $_MAVEN_SETTINGS -pl portfolio jetty:run
popd