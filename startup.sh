#!/bin/bash
docker build -t toures-bus:1.0 .
docker run -d -p 10000:10000 --name toures-bus toures-bus:1.0