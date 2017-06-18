#!/bin/bash

MAIN_DIR=src/main/kotlin/net/codetojoy/waro

set -e
trap "echo TRACER a command failed... probably compile error" ERR 

if [ -f backup/Main.kt ]; then
    if [ ! -f $MAIN_DIR/Main.kt ]; then 
        # restore backup from previous run which must have failed
        mv backup/Main.kt $MAIN_DIR
    else 
        # Main.kt exists in two places, which isn't good
        echo "illegal backup file detected ... reconcile Main.kt in backup and $MAIN_DIR"
        exit -1
    fi
fi

# clean up the main build
gradle clean
rm -rf node

# move Main.kt out of the way (it contains `require`)
mkdir -p backup
mv $MAIN_DIR/Main.kt backup

# the tests want JS in AMD mode
gradle -b test.build.gradle clean build

# restore Main.kt
mv backup/Main.kt $MAIN_DIR

echo ""
echo "OK ----->>>>>   refresh test.html to run QUnit tests"
