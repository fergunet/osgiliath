#!/bin/bash

# Variables
# $1 map
set -x
tree="${1}"
folder=/home/pgarcia/workspace/PlanetWars/environment
map=${2}
 java -jar ${folder}/PlayGame-1.2.jar 2> ${folder}/error${3}.txt ${folder}/maps/${map} 1000 1000 ${folder}/log${3}.txt "java -jar ${folder}/gpagent.jar $tree " "java -jar ${folder}/GenebotEje8.jar"
#java -jar ${folder}/PlayGame-1.2.jar 2> ${folder}/error.txt ${folder}/maps/map1.txt 1000 1000 log.txt "java -jar ${folder}/BullyBot.jar" "java -jar ${folder}/BullyBot.jar"
echo "Termino"
