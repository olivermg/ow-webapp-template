#!/usr/bin/env bash

ENVFILE=.env

source $ENVFILE
export $(cut -d= -f1 $ENVFILE)

lein repl

