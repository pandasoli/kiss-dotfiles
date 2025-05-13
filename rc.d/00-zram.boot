#!/bin/sh -e

setup() {
	# Create four zram devices for swap, one for tmpfs.
	modprobe zram num_devices=5

	# Create zram devices of size 2G
	# Use lz4 compression on the devices
	# Mark the devices as swap devices
	# Enable the swap devices

	for dev in 0 1 2 3; do
		echo lz4 > /sys/block/zram$dev/comp_algorithm
		echo 2G  > /sys/block/zram$dev/disksize
		mkswap     /dev/zram$dev
		swapon     /dev/zram$dev -p 10
	done

	# Create a 4G ext4 zram device to use as tmpfs with lz4 compression.
	echo lz4 > /sys/block/zram4/comp_algorithm
	echo 4G  > /sys/block/zram4/disksize
	mkfs.ext4  /dev/zram4
	mount      /dev/zram4 /tmp

	# /tmp requires sticky bit permission for nonroot user access.
	chmod 1777 /tmp
}

run() {
	setup > err 2>&1

	# /tmp ins't mounted until setup finishes.
	mv err /tmp/zram.log
}

run &
