#!/bin/sh -e

setup() {
	modprobe snd_hda_intel
	sleep 1

	chown root:audio /dev/snd/*
	chmod 660 /dev/snd/*
}

setup > /tmp/snd_hda_intel.log 2>&1 &
