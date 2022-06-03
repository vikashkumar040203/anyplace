#!/bin/bash
OUT=./build/outputs/apk/debug/
APK=$OUT/smas-debug.apk
DIR_PUBLISH=~/Dropbox/DMSL/anyplace-cv-imu/
DIR_LPROP=../local.properties

if [ ! -f $DIR_LPROP ]; then
  echo "no ../local.properties file found"
  exit
fi

rm $APK
gradle assembleDebug
