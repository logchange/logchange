#!/usr/bin/env bash
set -e

MVN="mvn --batch-mode"
$MVN clean verify
