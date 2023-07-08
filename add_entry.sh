#!/bin/sh

# Default values
fileName=""
title=""
author=""
type=""
linkName=""
linkUrl=""

# Function to parse the arguments
parse_arguments() {
  while [[ $# -gt 0 ]]; do
    case "$1" in
      -DfileName=*)
        fileName="${1#*=}"
        ;;
      -Dtitle=*)
        title="${1#*=}"
        ;;
      -Dauthor=*)
        author="${1#*=}"
        ;;
      -Dtype=*)
        type="${1#*=}"
        ;;
      -Dlink.name=*)
        linkName="${1#*=}"
        ;;
      -Dlink.url=*)
        linkUrl="${1#*=}"
        ;;
      *)
        echo "Invalid argument: $1"
        exit 1
        ;;
    esac
    shift
  done
}

# Function to validate the required arguments
validate_arguments() {
  if [[ -z "$fileName" || -z "$title" || -z "$author" || -z "$type" ]]; then
    echo "Missing required arguments."
    exit 1
  fi
}

# Function to replace dots with underscores and slashes with pipes in the file name
sanitize_file_name() {
  fileName="${fileName//./_}"
  fileName="${fileName//\//_}"
  fileName="${fileName//.yml/}"
  fileName="${fileName//.yaml/}"
  fileName="changelog/unreleased/${fileName}.yml"
}

# Function to create the entry file
create_entry_file() {
  echo $fileName
  if [ -n "$fileName" ]; then
    touch "$fileName"
    echo "# This file is used by logchange tool to generate CHANGELOG.md 🌳 🪓 => 🪵" >> "$fileName"
    echo "# Visit https://github.com/logchange/logchange and leave a star 🌟" >> "$fileName"
    echo "# More info about configuration you can find at https://github.com/logchange/logchange#yaml-format ⬅️⬅️" >> "$fileName"
    echo "title: $title" >> "$fileName"
    echo "authors:" >> "$fileName"
    echo "  - nick: $author" >> "$fileName"
    echo "type: $type" >> "$fileName"

    if [ -n "$linkName" ] && [ -n "$linkUrl" ]; then
      echo "links:" >> "$fileName"
      echo "  - name: $linkName" >> "$fileName"
      echo "    url: $linkUrl" >> "$fileName"
    fi
  fi
}

# Main execution
parse_arguments "$@"
validate_arguments
sanitize_file_name
create_entry_file