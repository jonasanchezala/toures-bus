#!/bin/bash
docker build -t toures-bus:1.0 .
docker run -d -p 8082:8082 --name toures-bus toures-bus:1.0