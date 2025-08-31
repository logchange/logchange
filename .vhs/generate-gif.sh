#!/usr/bin/env bash
set -euo pipefail

# Image name for your custom VHS build
IMAGE_NAME="vhs-with-brew"

# Build the image (Dockerfile assumed in same directory)
echo "👉 Building Docker image: $IMAGE_NAME"
docker build -t "$IMAGE_NAME" .

# Run VHS inside container
docker run --rm \
  -v "$PWD/demo-project":/vhs -w /vhs "$IMAGE_NAME" logchange-demo.tape

# Cleanup
sudo rm -fr demo-project/changelog # files created by docker are as root, but chromium inside docker need root
sudo rm -fr demo-project/CHANGELOG.md
