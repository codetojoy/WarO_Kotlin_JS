
# clean up the main build
gradle clean
rm -rf node

# move Main.kt out of the way (it contains `require`)
mkdir backup
mv src/main/kotlin/net/codetojoy/waro/Main.kt backup

# the tests want JS in AMD mode
gradle -b test.build.gradle clean build

# restore Main.kt
mv backup/Main.kt src/main/kotlin/net/codetojoy/waro

echo ""
echo "OK ----->>>>>   refresh test.html to run QUnit tests"
