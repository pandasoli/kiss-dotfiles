#!/bin/sh -e

setup() {
	modprobe uvcvideo
	sleep 1

	chown root:audio /dev/video*
	chmod 660 /dev/video*
}

setup > /tmp/uvcvideo.log 2>&1 &
