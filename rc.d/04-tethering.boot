#!/bin/sh -e

setup() {
	modprobe usbnet
	modprobe cdc_ether
	modprobe rndis_host
}

setup > /tmp/tethering.log 2>&1 &
