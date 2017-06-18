#!/bin/bash

if [ ! -f src/main/kotlin/net/codetojoy/waro/Main.kt ]; then
    echo "can't find Main.kt ... check backup folder"
    exit -1
fi

gradle clean build
node node/index.js

