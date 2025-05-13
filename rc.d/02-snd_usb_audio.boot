#!/bin/sh -e

setup() {
	modprobe snd_usb_audio
	sleep 1

	chown root:audio /dev/snd/*
	chmod 660 /dev/snd/*
}

setup > /tmp/snd_usb_audio.log 2>&1 &
